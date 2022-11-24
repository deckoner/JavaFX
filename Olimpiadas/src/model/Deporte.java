package model;

public class Deporte {
	
	private String nombre;
	private int id;
	
	public Deporte(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
		
	}

	public String getNombre() {
		return nombre;
		
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		
	}

	public int getId() {
		return id;
		
	}

	public void setId(int id) {
		this.id = id;
		
	}

	@Override
	public String toString() {
		return id +"-" + nombre;
	}
}
