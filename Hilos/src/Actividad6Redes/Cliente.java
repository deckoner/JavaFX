package Actividad6Redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main (String[]args){

		String Host="localhost";

		int Puerto = 6000;
		
		Consola c = new Consola();

		System.out.println("PROGRAMA CLIENTE INICIANDO");

		try {

			Socket Cliente = new Socket (Host, Puerto);
			
			// Flujo de salida al servidor
			DataOutputStream flujoSalida = new DataOutputStream (Cliente.getOutputStream());
			
			// preguntamos el numero al usuario
			System.out.println("Introduce un número");
			int n = c.leeInt();
			
			// Se envia el numero al servidor
			flujoSalida.writeUTF(String.valueOf(n));
			
			// Creación del flujo de entrada al servidor
			DataInputStream flujoEntrada = new DataInputStream (Cliente.getInputStream());

			// Recibimos el mensaje enviado por el servidor
			System.out.println("Recibiendo mensaje del servidor: \n\t" + 
					"El cuadrado del número " + n + " es " + flujoEntrada.readUTF());

			// Cerrar streams y sockets
			flujoEntrada.close();
			flujoSalida.close();
			Cliente.close();

		} catch (UnknownHostException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}
