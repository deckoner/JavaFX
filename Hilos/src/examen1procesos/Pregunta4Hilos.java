package examen1procesos;

public class Pregunta4Hilos extends Thread {
	@Override
	public void run() {
		Pregunta4Hilo.anadir();
	}
} 