package Project;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
	private Proxy proxy;
	private int id;
	private int maxPortionSize;
	private Future result;
	
	Consumer(int id, Proxy proxy, int bufferSize) {
		this.id = id;
		this.proxy = proxy;
		this.maxPortionSize = bufferSize/2;
	}
	
	
	@Override
	public void run() {
		int i = 0;
		while (i < 10) {
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			int portionSize = rand.nextInt(1,maxPortionSize);
			//int portionSize = 200;
			//int portionSize = ((i * 20) + 1) % maxPortionSize;
			result = (proxy.getPortion(portionSize));
			result.awaitDone();
			System.out.println("Consumer [" + id + "] got " + portionSize +" numbers from buffor!");
			i++;
		}
	}
}
