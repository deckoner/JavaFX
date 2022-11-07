package model;

public class Deportista {
	
	private int id, peso, altura;
	private String nombre, genero, equipo;
	
	public Deportista(int id, int peso, int altura, String nombre, String genero, String equipo) {
		super();
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.nombre = nombre;
		this.genero = genero;
		this.equipo = equipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
}


