package examen1procesos;

public class  Pregunta4Hilo {
	static int variable = 0;
	static synchronized void anadir() {
		variable++;
		System.out.println("Variable: " + variable);
	}
}

