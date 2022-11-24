package model;

public class Participacion {
	
	private int idDeportista, idEvento, idEquipo, edad;
	private String medalla, nombreDeportista, nombreEvento, nombreEquipo;
	
	public Participacion(int idDeportista, int idEvento, int idEquipo, int edad, String medalla,
			String nombreDeportista, String nombreEvento, String nombreEquipo) {
		
		this.idDeportista = idDeportista;
		this.idEvento = idEvento;
		this.idEquipo = idEquipo;
		this.edad = edad;
		this.medalla = medalla;
		this.nombreDeportista = nombreDeportista;
		this.nombreEvento = nombreEvento;
		this.nombreEquipo = nombreEquipo;
	}

	public int getIdDeportista() {
		return idDeportista;
	}

	public void setIdDeportista(int idDeportista) {
		this.idDeportista = idDeportista;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getMedalla() {
		return medalla;
	}

	public void setMedalla(String medalla) {
		this.medalla = medalla;
	}

	public String getNombreDeportista() {
		return nombreDeportista;
	}

	public void setNombreDeportista(String nombreDeportista) {
		this.nombreDeportista = nombreDeportista;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
}
