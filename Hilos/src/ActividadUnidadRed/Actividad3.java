package ActividadUnidadRed;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad3 {
	public static void main(String[] args) {
		
		String url= "";
		
		InetAddress address1, hots;
		InetAddress[] address2;

		
		if (!url.equals("")) {
			try {
				address1 = InetAddress.getByName(url);
				System.out.println("Dirección IP:" + address1.getHostAddress());
				System.out.println("Nombre:" + address1.getHostName());
				System.out.println(address1);
				System.out.println(address1.getHostAddress());
				System.out.println(address1);

			} catch (UnknownHostException e) {
				System.out.println("Hubo un error con la conecxion");
				
			}
		} else {
			try {
				hots = InetAddress.getByName("localhost");
				System.out.println("Dirección IP:" + hots.getHostAddress());
				System.out.println("Nombre:" + hots.getHostName());
				System.out.println(hots);
				System.out.println(hots.getHostAddress());
				
				
				address2 = InetAddress.getAllByName("localhost");
				
				for (int i = 0; i < address2.length; i++) {
					System.out.println(address2[i]);
					
				}
				
			} catch (UnknownHostException e) {
				System.out.println("Hubo un error con la conecxion");
				
			}
		}
	}
}
