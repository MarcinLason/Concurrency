package Lab2Home;


public class Philosopher implements Runnable {
	private SemaphoreLokaj lokaj;
	private BinarySemaphore [] widelce;
	private int id;
	
	public Philosopher(SemaphoreLokaj lokaj, int value, BinarySemaphore[] widelce) {
		this.lokaj = lokaj;
		this.id = value;
		this.widelce = widelce;
	}
	
	@Override
	public void run() {
		while(true) {
			lokaj.PB(id);
			System.out.println("Philosopher number " + this.id + " is thinking...");
			widelce[id].P();
			widelce[(id+1)%5].P();
			System.out.println("Philosopher number " + this.id + " is eating...");
			widelce[id].V();
			widelce[(id+1)%5].V();
			lokaj.VB(id);
		}
	}
	

}
