package EjercicioHilos4;

public class Posicion implements Runnable {
	
	String posicion;
	
	public Posicion(String posicion) {
		super();
		this.posicion = posicion;
	}

	public void run() {
		for (int i=1;i<100;i++)
		System.out.println( this.posicion + " " + i );
	}
}
