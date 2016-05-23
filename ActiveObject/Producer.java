package Project;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
	private int id;
	private int maxPortionSize;
	private Proxy proxy;
	private Future result;
	
	Producer(int id, Proxy proxy, int bufforSize) {
		this.proxy = proxy;
		this.maxPortionSize = bufforSize/2;	
		this.id = id;
	}
	
	
	@Override
	public void run() {
		int j = 0;
		while (j < 10) {
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			int portionSize = rand.nextInt(1, maxPortionSize);
			//int portionSize = 200;
			//int portionSize = ((j * 20) + 1) % maxPortionSize;
			Integer[] portion = new Integer[portionSize];
			
			for(int i = 0; i < portion.length; i++) {
				portion[i] = new Integer(rand.nextInt());
			}		
			
			result = (proxy.putPortion(portion));
			result.awaitDone();
			System.out.println("Producer[" + id + "] successfully put " + portion.length + " numbers to the buffor!");	
			j++;
		}
	}

}
