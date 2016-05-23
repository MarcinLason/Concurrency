package ConsProdsProject;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
	private int id;
	private int maxPortionSize;
	private Monitor monitor;
	
	
	public Producer(int id, Monitor monitor, int bufferSize) {
		this.id = id;
		this.monitor = monitor;
		this.maxPortionSize = bufferSize/2;
	}


	@Override
	public void run() {
		int j = 0;
		while (j < 10) {
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			int portionSize = (j*17)%maxPortionSize + 1;
			//int portionSize = 200;
			Integer [] portion = new Integer[portionSize];
			
			for(int i = 0; i < portion.length; i++) {
				portion[i] = new Integer(rand.nextInt());
			}
			monitor.put(portion);
			System.out.println("Producer[" + id + "] successfully put " + portion.length + " numbers to the buffor!");
			j++;
		}
		
	}
	
	
}
