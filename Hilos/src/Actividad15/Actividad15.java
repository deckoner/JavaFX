package Actividad15;

public class Actividad15 {
	public static void main(String[] args) {
		
		HiloTiempo h1 = new HiloTiempo(1);
		HiloTiempo h2 = new HiloTiempo(2);
		HiloTiempo h3 = new HiloTiempo(3);
		
		h1.start();
		h2.start();
		h3.start();
	}
}
