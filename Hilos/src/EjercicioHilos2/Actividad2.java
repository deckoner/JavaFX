package EjercicioHilos2;

public class Actividad2 {
	public static void main(String[] args) {	

        for (int f = 1; f <= 20; f++) {
        	
            Hilo h = new Hilo(f);
            h.start();  	
        }

        System.out.println("Final programa");
	}
}

class Hilo extends Thread {
	
	int posicion;

    public Hilo(int n) {
    	this.posicion = n;
    }

	@Override
    public void run() {
        for (int f = 1; f <= 20; f++)
            System.out.println("Hilo " + posicion);
    }
}