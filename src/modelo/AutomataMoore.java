package modelo;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;



public class AutomataMoore extends Automata {


	public HashMap<Integer, EstadoSalida> Estados;

	
	public Estado[][] matriz;

	
	public int cantidadEstado;

	
	public int cantidadSobra;

	
	public String imprimirParticion;


	public AutomataMoore() {
		imprimirParticion = "";
		Estados = new HashMap<Integer, EstadoSalida>();
		cantidadEstado = 0;
		cantidadSobra = 0;
	}


	public AutomataMoore(int sobra) {
		Estados = new HashMap<Integer, EstadoSalida>();
		cantidadEstado = 0;
		imprimirParticion = "";
		cantidadSobra = sobra;
	}


	public void add_Estado(String Salida) {

		EstadoSalida s = new EstadoSalida("q" + (cantidadEstado + cantidadSobra), Salida);
		Estados.put(cantidadEstado, s);
		cantidadEstado++;
		inicializarMatriz();
	}


	public int encontrarEstado(String Nombre) {

		for (int i = 0; i < Estados.size(); i++) {
			if (Estados.get(i).getNombre().equals(Nombre)) {
				return i;
			}
		}
		return -1;
	}

	public boolean deleted_Estado(String Estado) {
		int x = encontrarEstado(Estado);

		if (x > -1) {
			Estados.remove(x);
			return true;
		}
		return false;
	}

	public boolean modificacionNombre(String old_Nombre, String new_Nombre) {
		int x = encontrarEstado(old_Nombre);

		if (x > -1) {
			Estados.get(x).setNombre(new_Nombre);
			return true;
		}
		return false;
	}

	public void change_Salida_Estado(String Estado, String Salida) {

		int x = encontrarEstado(Estado);

		if (x > -1) {
			Estados.get(x).setNombre(Salida);
		}
	}
	



	public void inicializarMatriz() {
		matriz = new Estado[Estados.size()][simbolosEntrada.size()];
	}

	public void add_transition(String initial_Estado, String final_Estado, String input) {

		if (matriz == null) {
			inicializarMatriz();
		}

		int row = encontrarEntrada(input);
		int column = encontrarEstado(initial_Estado);
		int fs = encontrarEstado(final_Estado);

		if (row > -1 && column > -1 && fs > -1) {
			Estado s = new Estado(final_Estado);
			matriz[column][row] = s;
		}
	}


	public boolean not_Null_matriz() {

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
		print += ("\n");

		for (int x = 0; x < matriz.length; x++) {
			print += (Estados.get(x).getNombre() + "/" + Estados.get(x).getSalida() + "|\t");

			for (int y = 0; y < matriz[x].length; y++) {
				Estado so = matriz[x][y];

				if (so == null) {
					print += ("NULL\t");
				} else {
					print += (so.getNombre() + "\t");
				}
			}
			print += ("|");
			print += ("\n");
		}

		return print;
	}

	public ArrayList<String> estadosAlcanzables(String s) {
		ArrayList<String> list = new ArrayList<String>();
		int position = encontrarEstado(s);
		for (int i = 0; i < simbolosEntrada.size(); i++) {
			Estado x = matriz[position][i];
			if (!x.getNombre().equals(s)) {
				list.add(x.getNombre());
			}
		}

		return list;
	}

	public void eliminacionEstadoNoAccesibles() {

		HashMap<String, Boolean> map = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		ArrayList<String> r = new ArrayList<String>();

		map.put(Estados.get(0).getNombre(), false);
		queue.add(Estados.get(0).getNombre());

		while (!queue.isEmpty()) {
			String u = queue.poll();
			r.add(u);

			ArrayList<String> achivable = estadosAlcanzables(u);
			for (int i = 0; i < achivable.size(); i++) {

				if (!map.containsKey(achivable.get(i))) {
					map.put(achivable.get(i), false);
					queue.add(achivable.get(i));

				}
			}

			map.replace(u, true);
		}

		Estado[][] aux = new Estado[r.size()][simbolosEntrada.size()];
		HashMap<Integer, EstadoSalida> aux2 = new HashMap<Integer, EstadoSalida>();

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

	// ******************************************************************************
	// ***************** The following methods work on particions *******************
	// ******************************************************************************

	public ArrayList<ArrayList<String>> primerParticion() {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<String> particion = null;

		for (int i = 0; i < matriz.length; i++) {
			if (!visited.contains(i)) {
				particion = new ArrayList<String>();
				particion.add(Estados.get(i).getNombre());
				visited.add(i);

				for (int j = 0; j < matriz.length; j++) {

					if (!visited.contains(j)) {

						if (Estados.get(i).getSalida().equals(Estados.get(j).getSalida())) {
							particion.add(Estados.get(j).getNombre());
							visited.add(j);
						}
					}
				}
				ret.add(particion);
			}
		}
		return ret;
	}


	public ArrayList<ArrayList<String>> particion() {

		ArrayList<ArrayList<String>> original = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> siguiente = primerParticion();
		imprimirParticion = "";
		
		while (original.size() != siguiente.size()) {

			original = siguiente;
			siguiente = new ArrayList<ArrayList<String>>();
			imprimirParticion += (añadirParticion(original));

			for (int i = 0; i < original.size(); i++) { // A particions

				ArrayList<String> aux = new ArrayList<String>();
				ArrayList<String> not = new ArrayList<String>();
				aux.add(original.get(i).get(0));

				for (int j = 1; j < original.get(i).size(); j++) {// Estado
					boolean same = true;
					for (int m = 0; m < matriz[0].length; m++) { // simbolosEntrada

						String Estado1 = matriz[encontrarEstado(original.get(i).get(0))][m].getNombre();
						String Estado2 = matriz[encontrarEstado(original.get(i).get(j))][m].getNombre();

						for (int n = 0; n < original.size(); n++) { // All particion

							if (original.get(n).contains(Estado1)) {

								if (!original.get(n).contains(Estado2)) {
									not.add(original.get(i).get(j));
									m = matriz[0].length;
									same = false;
								}
							}
						}
					}
					
					if (same) {
						aux.add(original.get(i).get(j));
					}
				}
				siguiente.add(aux);
				if (not.size() > 0) {
					siguiente.add(not);
				}
			}

		}
		imprimirParticion += (añadirParticion(siguiente));
		return siguiente;
	}

}
