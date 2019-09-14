package modelo;

public class EstadoSalida{

	private String nombre;
	private String salida;
	
	public EstadoSalida(String nombre , String salida) {
		this.nombre = nombre;
		this.salida = salida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}
	
}