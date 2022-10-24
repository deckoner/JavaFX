package model;

public class Avion {
	
	String modelo;
	
	int id, nAsientos, vMaxima, idAeropuerto;
	
	boolean activado;

	public Avion(String modelo, int id, int nAsientos, int vMaxima, boolean activado, int idAeropuerto) {
		super();
		this.modelo = modelo;
		this.id = id;
		this.nAsientos = nAsientos;
		this.vMaxima = vMaxima;
		this.activado = activado;
		this.idAeropuerto = idAeropuerto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getnAsientos() {
		return nAsientos;
	}

	public void setnAsientos(int nAsientos) {
		this.nAsientos = nAsientos;
	}

	public int getvMaxima() {
		return vMaxima;
	}

	public void setvMaxima(int vMaxima) {
		this.vMaxima = vMaxima;
	}

	public int getIdAeropuerto() {
		return idAeropuerto;
	}

	public void setIdAeropuerto(int idAeropuerto) {
		this.idAeropuerto = idAeropuerto;
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}
}
