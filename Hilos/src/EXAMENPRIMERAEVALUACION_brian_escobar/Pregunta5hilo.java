package EXAMENPRIMERAEVALUACION_brian_escobar;

public class Pregunta5hilo extends Thread {
	
	String variable;
	
	public Pregunta5hilo(String variable) {
		this.variable = variable;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 3; i++) {
			System.out.println(variable + i);

		}
	}
}
