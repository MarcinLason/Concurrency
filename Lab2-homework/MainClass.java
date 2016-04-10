package Lab2Home;

public class MainClass {

	public static void main(String[] args) {
		BinarySemaphore[] widelce = new BinarySemaphore[5];
		for(int i = 0; i < 5; i++){
			widelce[i] = new BinarySemaphore(true);
		}
		
		SemaphoreLokaj LOKAJ = new SemaphoreLokaj(4);
		Philosopher Filozof1 = new Philosopher(LOKAJ, 0, widelce);
		Philosopher Filozof2 = new Philosopher(LOKAJ, 1, widelce);
		Philosopher Filozof3 = new Philosopher(LOKAJ, 2, widelce);
		Philosopher Filozof4 = new Philosopher(LOKAJ, 3, widelce);
		Philosopher Filozof5 = new Philosopher(LOKAJ, 4, widelce);
		
		Thread t1 = new Thread(Filozof1);
		Thread t2 = new Thread(Filozof2);
		Thread t3 = new Thread(Filozof3);
		Thread t4 = new Thread(Filozof4);
		Thread t5 = new Thread(Filozof5);
		
		t2.start();
		t1.start();
		t3.start();
		t4.start();
		t5.start();
		
	}

}
