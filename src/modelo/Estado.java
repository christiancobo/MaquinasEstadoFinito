package modelo;

public class Estado{

	private String nombre ;
	
	public Estado(String nombre ) {
		this.nombre = nombre;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
}