package Actividad6Redes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main (String[]args){

		String host="localhost";

		int puerto = 6010;

		try {
			//Socket para el cliente en localhost en puerto 1234
			Socket cs = new Socket(host, puerto);

            //Flujo de datos hacia el servidor
			DataOutputStream salidaServidor = new DataOutputStream(cs.getOutputStream());
			
            //Se escribe en el servidor usando su flujo de datos
            salidaServidor.writeUTF("Contigo pipo");

            cs.close();//Fin de la conexión

		} catch (UnknownHostException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
}
