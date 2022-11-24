package Actividad6Redes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main (String[]args){

		int puerto = 6010;

		try {
			ServerSocket ss = new ServerSocket(puerto); //Socket del servidor
			Socket cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

			
			System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            DataOutputStream salidaCliente = new DataOutputStream(cs.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");

            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            
            //Creamos el String del mensaje
            String mensajeServidor;
            
            //Mientras haya mensajes desde el cliente
            while((mensajeServidor = entrada.readLine()) != null)
            {
                //Se muestra por pantalla el mensaje recibido
                System.out.println(mensajeServidor);
            }

            System.out.println("Fin de la conexión");

            ss.close();//Se finaliza la conexión con el cliente

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} 
	}
}
