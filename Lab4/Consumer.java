package Lab4;

import java.util.Random;

public class Consumer implements Runnable {
	private Monitor monitor;
	private int maxPortionSize;
	private int portionSize;
	private int id;
	private Random r = new Random();
	
	Consumer(Monitor m, int id, int buffSize) {
		this.monitor = m;
		this.maxPortionSize = buffSize/2;	
		this.id = id;
	}

	@Override
	public void run() {
		while(true){
			portionSize = r.nextInt(maxPortionSize);
			monitor.endConsume(monitor.startConsume(portionSize), id);
		}	
	}
}
