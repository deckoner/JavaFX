package EjercicioHilos2;

public class Ejemplo extends Thread {
	
	public Ejemplo(String str) {
		super(str);
	}
	
	public void run() {
		for (int i = 0; i < 11 ; i++)
			System.out.println(i + " " + getName());
			System.out.println("Termina thread " + getName());
	}
	
	public static void main (String [] args) {
		new Ejemplo("Primero").start();
		new Ejemplo("Segundo").start();
		System.out.println("Termina thread main");
	}
}