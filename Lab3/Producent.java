package Lab3;

import java.util.Random;

public class Producent implements Runnable {
	Random randomGenerator = new Random();
	Monitor monitor;
	
	public Producent(Monitor m) {
		this.monitor = m;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			monitor.wstaw(randomGenerator.nextInt(100));
		}
	}

}
