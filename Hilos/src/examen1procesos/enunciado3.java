package examen1procesos;

public class enunciado3 {

	public static void main(String[] args) {

		Runtime r = Runtime.getRuntime();	
		
		String comando = "firefox";
				
		Process p;
		
		try {
			
			p = r.exec(comando);
		
		}
		catch (Exception e) {
			System.out.println("Error en: " + comando);
			e.printStackTrace();
		}
	}

}
