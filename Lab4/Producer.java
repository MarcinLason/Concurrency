package Lab4;

import java.util.Random;

public class Producer implements Runnable{
	private Monitor monitor;
	private int id;
	private int portionSize;
	private int maxPortionSize;
	private Random r = new Random();
	
	public Producer(Monitor m, int id, int buffSize) {
		this.monitor = m;
		this.id = id;
		this.maxPortionSize = buffSize/2;
	}
	

	@Override
	public void run() {
		while(true){
			portionSize = r.nextInt(maxPortionSize);
			monitor.endProduce(monitor.startProduce(portionSize), id);
		}
		
	}
}
