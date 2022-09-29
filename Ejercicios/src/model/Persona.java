package model;

public class Persona {
	
	private String nombre, apellidos;
	private int edad;
	
	public Persona(String nombre, String apellidos, int edad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}
	
	public boolean compararPersona(Persona p2) {
		if (this.nombre.equals(p2.getNombre()) && 
			(this.apellidos.equals(p2.getApellidos())) &&
			(this.edad == p2.getEdad())){
			
			return true;
		} else {
			
			return false;
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
