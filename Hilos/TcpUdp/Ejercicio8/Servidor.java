package Ejercicio8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class Servidor {

	public static void main (String[] args) 
	{		
		int puerto = 12358;
		byte buffer[] = new byte[1024];
		
		try 
		{
			// Recibimos el mensaje del cliente
			DatagramSocket socket = new DatagramSocket(puerto);
			System.out.println("Esperando datagrama...");
			DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
			socket.receive(recibo);
			
			// Lo pasamos a un int
			ByteBuffer bbRecibo = ByteBuffer.wrap(buffer);
			int numRecibido = bbRecibo.getInt();
			
			// Dibujamos los mensjes por pantalla
			System.out.println("Vamos a calcular el cubo de: " + numRecibido);
			System.out.println("IP de origen: " + recibo.getAddress().getHostAddress());
			System.out.println("Puerto de origen: " + recibo.getPort());
			
			// Para enviar el resultatado
			int resultado = (int)(Math.pow(numRecibido, 2));
			ByteBuffer bbEnvio = ByteBuffer.allocate(4);
			bbEnvio.putInt(resultado);
			byte bufferResultado[] = bbEnvio.array();
			DatagramPacket envio = new DatagramPacket(bufferResultado, bufferResultado.length, recibo.getAddress(), 34571);
			
			// Enviamos el resultado
			socket.send(envio);
			System.out.println("Enviamos el resultado...");
			
			// Cerramos el socket
			System.out.println("Adiósss");
			socket.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	} 
}

