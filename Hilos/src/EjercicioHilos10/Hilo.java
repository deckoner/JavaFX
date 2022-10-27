package EjercicioHilos10;

public class Hilo extends Thread {
	public void run() {
		System.out.println("Ejecutando Hilo-Prioridad " + this.getPriority());
	}
}
