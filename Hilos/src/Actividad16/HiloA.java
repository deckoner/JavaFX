package Actividad16;

class HiloA extends Thread {
	
	private Contador contador;
	
	public HiloA (String n, Contador c) {
		setName(n);
		contador=c;
	}
	
	public void run () {
		for (int j=0; j < 400; j++) {
			
			contador.incrementa();
			
			try {
				
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		System.out.println(getName() + " contador vale" + contador.getValor());
		
		notify();
	}
}


