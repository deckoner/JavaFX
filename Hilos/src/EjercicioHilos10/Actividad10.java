package EjercicioHilos10;

public class Actividad10 {
	public static void main(String[] args) {
		
		Hilo h1 = new Hilo();
		Hilo h2 = new Hilo();
		
        Thread.currentThread().setPriority(5);		
		h1.setPriority(3);
		h2.setPriority(7);
        
        System.out.println("main tiene la prioridad " + Thread.currentThread().getPriority());
		System.out.println("Thread - 0 tiene la prioridad " + h1.getPriority());
		System.out.println("Thread - 1 tiene la prioridad " + h2.getPriority());
		
		h1.start();
		h2.start();

		System.out.println("Final programa");
	}
}
