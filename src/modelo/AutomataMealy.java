package modelo;


import java.lang.reflect.Array;
import java.util.*;

public class AutomataMealy extends Automata {

	// Key:Position in matrix, value: Estado
	public HashMap<Integer, Estado> Estados;

	// matriz whit transitions
	public EstadoSalida[][] matrix;

	// Number of the suffix available to create a Estado
	public int count_Estado;

	// Number of the suffix available to create a Estado
	public int count_carry;

	//String with all partitions
	public String print_partition;

	/**
	 * <h1>AutomataMealy constructor where attributes are initialized</h1>
	 */
	public AutomataMealy() {
		Estados = new HashMap<Integer, Estado>();
		print_partition = "";
		count_Estado = 0;
		count_carry = 0;
	}

	/**
	 * <h1>AutomataMealy constructor where attributes are initialized</h1>
	 */
	public AutomataMealy(int carry) {
		Estados = new HashMap<Integer, Estado>();
		count_Estado = 0;
		count_carry = carry;
		print_partition = "";
	}

	// ******************************************************************************
	// ***************** The following methods work on Estados *********************
	// ******************************************************************************

	/**
	 * Add a Estado into the AutomataMealy machine<br/>
	 * The first Estado added will be the initial Estado q0
	 */
	public void add_Estado() {
		Estado s = new Estado("q" + (count_Estado + count_carry));
		Estados.put(count_Estado, s);
		count_Estado++;
	}

	/**
	 * Add a Estado into the AutomataMealy machine<br/>
	 * The first Estado added will be the initial Estado q0
	 */
	public void add_Estado(int quantity) {
		for (int i = 0; i < quantity; i++) {
			add_Estado();
		}
	}

	/**
	 * this method find a Estado
	 * 
	 * @param Nombre Nombre of the Estado that you want to find
	 * @return position in the hash r<br/>
	 *         If the status is not found,-1 is returned
	 */
	public int find_Estado(String Nombre) {

		for (int i = 0; i < Estados.size(); i++) {
			if (Estados.get(i).getNombre().equals(Nombre)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * this method delete a Estado
	 * 
	 * @param number Number of the Estado that you want to delete
	 */
	public boolean deleted_Estado(String Estado) {
		int x = find_Estado(Estado);

		if (x > -1) {
			Estados.remove(x);
			return true;
		}
		return false;
	}

	/**
	 * this method Replaces the Nombre of a Estado with a new one
	 * 
	 * @param old_Nombre Current Estado Nombre
	 * @param new_Nombre Nombre that you want to give to the Estado
	 */
	public boolean reNombre_Estado(String old_Nombre, String new_Nombre) {
		int x = find_Estado(old_Nombre);

		if (x > -1) {
			Estados.get(x).setNombre(new_Nombre);
			return true;
		}
		return false;
	}

	// ******************************************************************************
	// ***************** The following methods work on matrix ***********************
	// ******************************************************************************

	/**
	 * This method initializes the matrix
	 */
	public void initialize_Matrix() {
		matrix = new EstadoSalida[Estados.size()][simbolosEntrada.size()];
	}

	/**
	 * This method adds a transition to the matrix
	 * 
	 * @param initial_Estado
	 * @param final_Estado
	 * @param input
	 * @param output
	 */
	public void add_transition(String initial_Estado, String final_Estado, String input, String output) {

		if (matrix == null) {
			initialize_Matrix();
		}

		int row = encontrarEntrada(input);
		int column = find_Estado(initial_Estado);
		int fs = find_Estado(final_Estado);
		
		if (row > -1 && column > -1 && fs > -1) {
			EstadoSalida s = new EstadoSalida(final_Estado, output);
			matrix[column][row] = s;
		}
	}

	/**
	 * This method verify that the AutomataMealy's matrix has an empty field
	 * 
	 * @return A boolean that represent the Estado the matrix <br/>
	 *         true: if the matrix its full<br/>
	 *         false: if the matrix have one or more empty field
	 */
	public boolean not_Null_Matrix() {

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[0].length; j++) {

				if (matrix[i][j] == null) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * This method retorn a strig which represent the AutomataMealy matrix
	 * 
	 * @return the string
	 */
	public String print_Matrix() {

		if (matrix == null) {
			initialize_Matrix();
		}

		String print = "";
		print += ("-\t");

		for (int x = 0; x < simbolosEntrada.keySet().size(); x++) {

			print += (simbolosEntrada.get(x) + "\t");
		}
		print += "\n";
		for (int x = 0; x < matrix.length; x++) {
			print += (Estados.get(x).getNombre() + "|\t");

			for (int y = 0; y < matrix[x].length; y++) {
				EstadoSalida so = matrix[x][y];

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

	/**
	 * This method find the Estados attainable for the Estado in the parameter
	 * 
	 * @param Estado : string with the Nombre of the Estado
	 * @return an Arraylist whit the Nombre of Estados attainable
	 */
	private ArrayList<String> Achievable_Estados(String Estado) {
		ArrayList<String> list = new ArrayList<String>();
		int position = find_Estado(Estado);
		for (int i = 0; i < matrix[0].length; i++) {
			EstadoSalida x = matrix[position][i];
			if (!x.getNombre().equals(Estado)) {
				list.add(x.getNombre());
			}
		}
		return list;
	}

	/**
	 * this method delete the Estados that q0 can't reach
	 */
	public void delete_Estados_Unreachable() {

		HashMap<String, Boolean> map = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		ArrayList<String> r = new ArrayList<String>();

		map.put(Estados.get(0).getNombre(), false);
		queue.add(Estados.get(0).getNombre());

		while (!queue.isEmpty()) {
			String u = queue.poll();
			r.add(u);

			ArrayList<String> achivable = Achievable_Estados(u);
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
					aux[k][j] = matrix[i][j];
				}
				k++;
			}
			
		}

		Estados = aux2;
		matrix = aux;
	}

	// ******************************************************************************
	// ***************** The following methods work on partitions *******************
	// ******************************************************************************

	/**
	 * this method calculate the first partition
	 * @return Array with sets of first partition 
	 */
	public ArrayList<ArrayList<String>> first_partition() {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<String> partition = null;
print_partition = "";
		
		for (int i = 0; i < matrix.length; i++) {
			if (!visited.contains(i)) {
				partition = new ArrayList<String>();
				partition.add(Estados.get(i).getNombre());
				visited.add(i);

				for (int j = 0; j < matrix.length; j++) {

					if (!visited.contains(j)) {

						boolean aux = true;
						for (int k = 0; k < matrix[0].length && aux; k++) {
							if (!matrix[i][k].getSalida().equals(matrix[j][k].getSalida())) {
								aux = false;
							}
						}
						if (aux) {
							partition.add(Estados.get(j).getNombre());
							visited.add(j);

						}
					}
				}
				ret.add(partition);
			}
		}
		return ret;
	}

	/**
	 * this method calculate the last partition
	 * @return Array with sets of last partition
	 */
	public ArrayList<ArrayList<String>> partition() {

		ArrayList<ArrayList<String>> original = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> next = first_partition();

		while (original.size() != next.size()) {

			original = next;
			next = new ArrayList<ArrayList<String>>();
			print_partition += (añadirParticion(original));

			for (int i = 0; i < original.size(); i++) { // A partitions

				ArrayList<String> aux = new ArrayList<String>();
				ArrayList<String> not = new ArrayList<String>();
				aux.add(original.get(i).get(0));

				for (int j = 1; j < original.get(i).size(); j++) {// Estado
					boolean same = true;
					for (int m = 0; m < matrix[0].length; m++) { // simbolosEntrada

						String Estado1 = matrix[find_Estado(original.get(i).get(0))][m].getNombre();
						String Estado2 = matrix[find_Estado(original.get(i).get(j))][m].getNombre();

						for (int n = 0; n < original.size(); n++) { // All partition

							if (original.get(n).contains(Estado1)) {

								if (!original.get(n).contains(Estado2)) {
									not.add(original.get(i).get(j));
									m = matrix[0].length;
									same = false;
								}
							}
						}
					}
					
					if (same) {
						aux.add(original.get(i).get(j));
					}
				}
				next.add(aux);
				if (not.size() > 0) {
					next.add(not);
				}
			}

		}
		print_partition += (añadirParticion(next));
		return next;
	}
}
