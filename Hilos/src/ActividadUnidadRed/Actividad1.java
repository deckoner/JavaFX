package ActividadUnidadRed;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad1 {
	public static void main(String[] args) {
				
		InetAddress[] address;
		
		try {
			System.out.println( "Direciones asociadas a Spotify" ); 
			address = InetAddress.getAllByName( "www.spotify.com" );
			
			for (int i = 0; i < address.length; i++) {
				System.out.println(address[i]);
			}
						
		} catch (UnknownHostException e) {
			System.out.println("Hubo un error con la conecxion");
			
		}
	}
}
