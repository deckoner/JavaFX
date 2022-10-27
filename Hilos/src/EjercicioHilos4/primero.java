package EjercicioHilos4;

public class primero implements Runnable {
	public void run() {
		for (int i=1;i<100;i++)
			System.out.println( "Primero " + i );
		}
}
