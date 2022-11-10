package ActividadUnidadRed;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad2 {
	public static void main(String[] args) {
		
		String url= "web.gencat.cat";
		
		InetAddress[] address;
		
		if (!url.equals("")) {
			try {
				System.out.println("Las direcciones asociadas a"+ url +" son:"); 
				
				address = InetAddress.getAllByName(url);
				
				for (int i = 0; i < address.length; i++) {
					System.out.println(address[i]);
				}
				
			} catch (UnknownHostException e) {
				System.out.println("Hubo un error con la conecxion");
				
			}
		} else {
			System.out.println("Se necesita una URL para obtener su dirección");
			
		}
	}
}
