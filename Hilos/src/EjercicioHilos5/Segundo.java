package EjercicioHilos5;

public class Segundo extends Thread {
	public void run() {
		int contador = 0;
		try {
			for (int i=1;i<15;i++) {
				System.out.println( "Segundo " + i );
				
				contador++;
				
				if (contador == 15) {
					contador = 0;
					this.sleep(200);
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
