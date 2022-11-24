package model;

public class Equipo {
	
	private int id;
	private String nombre,iniciales;
	public Equipo(int id, String nombre, String iniciales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.iniciales = iniciales;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIniciales() {
		return iniciales;
	}
	
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	@Override
	public String toString() {
		return id + " - " + nombre;
	}
}