package model;

public class Evento {
	
	private int id, idOlimpiada, idDeporte;
	private String nombre, olimpiada, deporte;
	
	public Evento(int id, int idOlimpiada, int idDeporte, String nombre, String olimpiada, String deporte) {
		super();
		this.id = id;
		this.idOlimpiada = idOlimpiada;
		this.idDeporte = idDeporte;
		this.nombre = nombre;
		this.olimpiada = olimpiada;
		this.deporte = deporte;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdOlimpiada() {
		return idOlimpiada;
	}
	
	public void setIdOlimpiada(int idOlimpiada) {
		this.idOlimpiada = idOlimpiada;
	}
	
	public int getIdDeporte() {
		return idDeporte;
	}
	
	public void setIdDeporte(int idDeporte) {
		this.idDeporte = idDeporte;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOlimpiada() {
		return olimpiada;
	}
	
	public void setOlimpiada(String olimpiada) {
		this.olimpiada = olimpiada;
	}
	
	public String getDeporte() {
		return deporte;
	}
	
	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}
}
