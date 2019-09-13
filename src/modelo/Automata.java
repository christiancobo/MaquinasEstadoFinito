package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Automata {
	
	HashMap<Integer, String> simbolosEntrada;
	private int cantidadEntrada;
	
	
	public Automata() {
		// TODO Auto-generated constructor stub
		simbolosEntrada = new HashMap<Integer, String>();
		cantidadEntrada = 0;
	}
	
	public void agregarEntrada(String entrada) {
		simbolosEntrada.put(cantidadEntrada, entrada);
		cantidadEntrada ++;
		
	}
	
	public void agregarEntradas(String entradas) {
		String[] conjunto = entradas.split(",");

		for (int i = 0; i < conjunto.length; i++) {
			agregarEntrada(conjunto[i]);
		}
	}
	
	protected int encontrarEntrada(String entrada) {

		Set<Integer> k = simbolosEntrada.keySet();

		for (Integer x : k) {
			if (simbolosEntrada.get(x).equals(entrada)) {
				return x;
			}
		}
		return -1;
	}
	
	
	public boolean change_input(String x, String y) {
		int termino = encontrarEntrada(y);

		if (termino > -1) {
			simbolosEntrada.replace(termino, y);
			return true;

		}

		return false;
	}
	
	public boolean EliminarEntrada(String entrada) {
		int x = encontrarEntrada(entrada);
		if (x > -1) {
			simbolosEntrada.remove(x);
			return true;
		}

		return false;
	}
	
	
	public String añadirParticion(ArrayList<ArrayList<String>>particion) {
		String cadena = "";
		for (int i = 0; i < particion.size(); i++) {
			cadena += "{";
			for (int j = 0; j < particion.get(i).size(); j++) {
				if(j!=particion.get(i).size()-1) {
					cadena += particion.get(i).get(j)+" ";
				}else {
					cadena += particion.get(i).get(j);
				}
			}
			cadena += "}  ";
		}
		
		cadena += "\n";
		return cadena;
	}
	
	

}
