package modelo;


import java.lang.reflect.Array;
import java.util.*;

public class AutomataMealy extends Automata {


	public HashMap<Integer, Estado> Estados;

	
	public EstadoSalida[][] matriz;


	public int cantidadEstado;


	public int cantidadSobra;


	public String cadenaParticion;

	public AutomataMealy() {
		Estados = new HashMap<Integer, Estado>();
		cadenaParticion = "";
		cantidadEstado = 0;
		cantidadSobra = 0;
	}

	public AutomataMealy(int carry) {
		Estados = new HashMap<Integer, Estado>();
		cantidadEstado = 0;
		cantidadSobra = carry;
		cadenaParticion = "";
	}

	public void añadirEstado() {
		Estado s = new Estado("q" + (cantidadEstado + cantidadSobra));
		Estados.put(cantidadEstado, s);
		cantidadEstado++;
	}

	public void añadirEstado(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			añadirEstado();
		}
	}


	public int encontrarEstado(String Nombre) {

		for (int i = 0; i < Estados.size(); i++) {
			if (Estados.get(i).getNombre().equals(Nombre)) {
				return i;
			}
		}
		return -1;
	}

	public boolean eliminarEstado(String Estado) {
		int x = encontrarEstado(Estado);

		if (x > -1) {
			Estados.remove(x);
			return true;
		}
		return false;
	}

	public boolean modificarNombreEstado(String old_Nombre, String new_Nombre) {
		int x = encontrarEstado(old_Nombre);

		if (x > -1) {
			Estados.get(x).setNombre(new_Nombre);
			return true;
		}
		return false;
	}

	public void inicializarMatriz() {
		matriz = new EstadoSalida[Estados.size()][simbolosEntrada.size()];
	}

	public void añadirTrancision(String initial_Estado, String final_Estado, String input, String output) {

		if (matriz == null) {
			inicializarMatriz();
		}

		int row = encontrarEntrada(input);
		int column = encontrarEstado(initial_Estado);
		int fs = encontrarEstado(final_Estado);
		
		if (row > -1 && column > -1 && fs > -1) {
			EstadoSalida s = new EstadoSalida(final_Estado, output);
			matriz[column][row] = s;
		}
	}


	public boolean verificacionMatriz() {

		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[0].length; j++) {

				if (matriz[i][j] == null) {
					return false;
				}
			}
		}

		return true;
	}

	public String imprimirMatriz() {

		if (matriz == null) {
			inicializarMatriz();
		}

		String print = "";
		print += ("-\t");

		for (int x = 0; x < simbolosEntrada.keySet().size(); x++) {

			print += (simbolosEntrada.get(x) + "\t");
		}
		print += "\n";
		for (int x = 0; x < matriz.length; x++) {
			print += (Estados.get(x).getNombre() + "|\t");

			for (int y = 0; y < matriz[x].length; y++) {
				EstadoSalida so = matriz[x][y];

				if (so == null) {
					print += ("NULL\t");
				} else {
					print += (so.getNombre() + "/" + so.getSalida() + "\t");
				}
			}
			print += ("|");
			print += ("\n");
		}
		return print;
	}


	private ArrayList<String> EstadosAlcanzables(String Estado) {
		ArrayList<String> list = new ArrayList<String>();
		int position = encontrarEstado(Estado);
		for (int i = 0; i < matriz[0].length; i++) {
			EstadoSalida x = matriz[position][i];
			if (!x.getNombre().equals(Estado)) {
				list.add(x.getNombre());
			}
		}
		return list;
	}

	public void eliminacionEstadosNoAccesibles() {

		HashMap<String, Boolean> map = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		ArrayList<String> r = new ArrayList<String>();

		map.put(Estados.get(0).getNombre(), false);
		queue.add(Estados.get(0).getNombre());

		while (!queue.isEmpty()) {
			String u = queue.poll();
			r.add(u);

			ArrayList<String> achivable = EstadosAlcanzables(u);
			for (int i = 0; i < achivable.size(); i++) {

				if (!map.containsKey(achivable.get(i))) {
					map.put(achivable.get(i), false);
					queue.add(achivable.get(i));

				}
			}

			map.replace(u, true);
		}

		EstadoSalida[][] aux = new EstadoSalida[r.size()][simbolosEntrada.size()];
		HashMap<Integer, Estado> aux2 = new HashMap<Integer, Estado>();

		int find = 0;

		for (int i = 0; i < Estados.size(); i++) {
			if (r.contains(Estados.get(i).getNombre())) {
				aux2.put(find, Estados.get(i));
				find++;
			}

		}
		int k = 0;
		for (int i = 0; i < Estados.size(); i++) {

			if(r.contains(Estados.get(i).getNombre())) {
				for (int j = 0; j < simbolosEntrada.size(); j++) {
					aux[k][j] = matriz[i][j];
				}
				k++;
			}
			
		}

		Estados = aux2;
		matriz = aux;
	}


	public ArrayList<ArrayList<String>> primerParticion() {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> visitado = new ArrayList<Integer>();
		ArrayList<String> partition = null;
cadenaParticion = "";
		
		for (int i = 0; i < matriz.length; i++) {
			if (!visitado.contains(i)) {
				partition = new ArrayList<String>();
				partition.add(Estados.get(i).getNombre());
				visitado.add(i);

				for (int j = 0; j < matriz.length; j++) {

					if (!visitado.contains(j)) {

						boolean aux = true;
						for (int k = 0; k < matriz[0].length && aux; k++) {
							if (!matriz[i][k].getSalida().equals(matriz[j][k].getSalida())) {
								aux = false;
							}
						}
						if (aux) {
							partition.add(Estados.get(j).getNombre());
							visitado.add(j);

						}
					}
				}
				ret.add(partition);
			}
		}
		return ret;
	}

	public ArrayList<ArrayList<String>> partition() {

		ArrayList<ArrayList<String>> original = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> siguiente = primerParticion();

		while (original.size() != siguiente.size()) {

			original = siguiente;
			siguiente = new ArrayList<ArrayList<String>>();
			cadenaParticion += (añadirParticion(original));

			for (int i = 0; i < original.size(); i++) { // A partitions

				ArrayList<String> aux = new ArrayList<String>();
				ArrayList<String> not = new ArrayList<String>();
				aux.add(original.get(i).get(0));

				for (int j = 1; j < original.get(i).size(); j++) {// Estado
					boolean igual = true;
					for (int m = 0; m < matriz[0].length; m++) { // simbolosEntrada

						String Estado1 = matriz[encontrarEstado(original.get(i).get(0))][m].getNombre();
						String Estado2 = matriz[encontrarEstado(original.get(i).get(j))][m].getNombre();

						for (int n = 0; n < original.size(); n++) { // All partition

							if (original.get(n).contains(Estado1)) {

								if (!original.get(n).contains(Estado2)) {
									not.add(original.get(i).get(j));
									m = matriz[0].length;
									igual = false;
								}
							}
						}
					}
					
					if (igual) {
						aux.add(original.get(i).get(j));
					}
				}
				siguiente.add(aux);
				if (not.size() > 0) {
					siguiente.add(not);
				}
			}

		}
		cadenaParticion += (añadirParticion(siguiente));
		return siguiente;
	}
}
