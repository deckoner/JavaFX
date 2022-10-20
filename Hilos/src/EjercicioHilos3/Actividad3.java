package EjercicioHilos3;

public class Actividad3 {
	public static void main(String[] args) {
		Hilo h1 = new Hilo();
        h1.start();
        
        System.out.println("El nombre del hilo es " + h1.getName() + " y tiene la prioridad " + h1.getPriority());
        
        h1.setName("SUPER-HILO-DM2");
        h1.setPriority(6);
        
        System.out.println("Ahora el nombre del hilo es " + h1.getName() + " y tiene la prioridad " + h1.getPriority());
        
        System.out.println("Final programa");
	}
}

class Hilo extends Thread  {
	
	@Override
    public void run() {
		int n = 0;
        for (int f = 1; f <= 200000; f++)
        	n = n + f;
    }
}
