 package EXAMENPRIMERAEVALUACION_brian_escobar;

public class Pregunta5 {
	public static void main(String[] args) {

		Pregunta5hilo t1 = new Pregunta5hilo("El 1: ==>");
		Pregunta5hilo t2 = new Pregunta5hilo("El 2: ==>");
		Pregunta5hilo t3 = new Pregunta5hilo("El 3: ==>");
		Pregunta5hilo t4 = new Pregunta5hilo("El 4: ==>");
		
		t1.setPriority(5);
		t2.setPriority(9);
		t3.setPriority(7);
		t4.setPriority(3);
		
		System.out.println("La prioridad de t1 es " + t1.getPriority());
		System.out.println("La prioridad de t2 es " + t2.getPriority());
		System.out.println("La prioridad de t3 es " + t3.getPriority());
		System.out.println("La prioridad de t4 es " + t4.getPriority());

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
