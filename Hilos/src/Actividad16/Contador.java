package Actividad16;

class Contador {
	
	private int c=0;
	boolean salir=false;
	
	Contador (int c) { 
		this.c = c; 
		
	}
	
	public synchronized void incrementa() {
        c+=1;
        
	}
	
	public synchronized void decrementa() {
        try{ 
        	
        	while (salir==false){
        		this.wait();
        		salir=true;
        		
        	}
        } catch( InterruptedException e ){}
        c--;
        
        this.notify();
	}
	
	public synchronized int getValor() {
		
		this.notify();
		return c; 
	}
}
