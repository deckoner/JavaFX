package Actividad15;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HiloTiempo extends Thread {
	
	private int numeroHiolo;

	public HiloTiempo(int numeroHiolo) {
		super();
		this.numeroHiolo = numeroHiolo;
	}
	
	public synchronized void run() {
		try {
			for (int i=0;i<5;i++) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();
				 
				
				System.out.println("Hilo " + numeroHiolo + " - " + dateFormat.format(date));
				//notify();
				wait(1000);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
