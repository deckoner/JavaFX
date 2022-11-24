package examen1procesos;

public class enunciado4 {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 5; i++) {
				Pregunta4Hilos p = new Pregunta4Hilos();
				Pregunta4Hilos s = new Pregunta4Hilos();
				Pregunta4Hilos t = new Pregunta4Hilos();
				p.start();
				s.start();
				t.start();
				}
			} catch (Exception e) {
		}
	}
}

