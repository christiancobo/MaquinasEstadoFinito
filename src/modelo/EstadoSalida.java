package modelo;

public class EstadoSalida{

	private String nombre;
	private String salida;
	
	public EstadoSalida(String nombre , String salida) {
		this.nombre = nombre;
		this.salida = salida;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public String getsalida() {
		return salida;
	}

	public void setsalida(String salida) {
		this.salida = salida;
	}
	
}