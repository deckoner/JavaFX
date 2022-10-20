package EjercicioHilos1;

public class Actividad1 {

    public static void main(String[] args) {
    	
    	String n = "Primero";
    	String n2 = "segundo";
    	
    	Hilo h1 = new Hilo(n);    	
    	Hilo h2 = new Hilo(n2);

        h1.start();        
        h2.start();


        System.out.println("Fin programa");
    }
}

class Hilo extends Thread {
	
	String posicion;

    public Hilo(String n) {
    	this.posicion = n;
    }

	@Override
    public void run() {
        for (int f = 1; f <= 20; f++)
            System.out.println(posicion + " " + f);
    }
}
