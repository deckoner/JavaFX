package model;

public class AeropuertoPublico {
	
	String nombre, pais, ciudad, calle;
	
	int id, capacidadNSocios, nAsientos, numero, age;

	public AeropuertoPublico(int id, String nombre, String pais, String ciudad, String calle,
			int numero, int age, int capacidadNSocios) {
		super();
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.id = id;
		this.capacidadNSocios = capacidadNSocios;
		this.numero = numero;
		this.age = age;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidadNsOCIOS() {
		return capacidadNSocios;
	}

	public void setCapacidadNsOCIOS(int capacidadNsOCIOS) {
		this.capacidadNSocios = capacidadNsOCIOS;
	}

	public int getnAsientos() {
		return nAsientos;
	}

	public void setnAsientos(int nAsientos) {
		this.nAsientos = nAsientos;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
