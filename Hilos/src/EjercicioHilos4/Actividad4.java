package EjercicioHilos4;

public class Actividad4 {
	public static void main(String arg[]) {
		Thread p = new Thread(new Posicion("Primero"));
		Thread s = new Thread(new Posicion("Segundo"));
		p.start();
		s.start();
		System.out.println( "Fin programa ");
		}
}
