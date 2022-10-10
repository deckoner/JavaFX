package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Persona {
	
	private SimpleStringProperty nombre;
	private SimpleStringProperty apellidos;
	private SimpleIntegerProperty edad;
	
	public Persona(String nombre, String apellidos, int edad) {
		this.nombre = new SimpleStringProperty(nombre);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.edad = new SimpleIntegerProperty(edad);
	}
	
	public boolean compararPersona(Persona p2) {
		if (this.nombre.get().equals(p2.getNombre()) && 
			(this.apellidos.get().equals(p2.getApellidos())) &&
			(this.edad.get() == p2.getEdad())){
			
			return true;
		} else {
			
			return false;
		}
	}
	
	public String getNombre() {
		return nombre.get();
	}
	
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	
	public String getApellidos() {
		return apellidos.get();
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos.set(apellidos);
	}
	
	public int getEdad() {
		return edad.get();
	}
	
	public void setEdad(int edad) {
		this.edad.set(edad);
	}
	
	public SimpleStringProperty propiedadNombre() {
		
		return this.nombre;
	}
	
	public SimpleStringProperty propiedadApellidos() {
		
		return this.apellidos;
	}
	
	public SimpleIntegerProperty propiedadEdad() {
		
		return this.edad;
	}
}
