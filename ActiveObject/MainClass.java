package Project;

import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		final int N = 50000; //number of consumers and producers
		final int SIZE = 2000; //servant's buffer size
		Proxy proxy = new Proxy(SIZE);
		List<Thread> threads = new ArrayList<Thread>(2 * N);
		
		
		for(int i = 0; i < N; i++) { // loop where N consumers and N producers are created.
			threads.add(new Thread(new Producer(i, proxy, SIZE)));
			threads.add(new Thread(new Consumer(i, proxy, SIZE)));
		}
		
		long startTime = System.currentTimeMillis();
		System.out.println("Systems starts working by starting " + N + " consumers and " + N + " producers.");
		
		
		for(Thread t : threads) { // starting all prepared consumers and producers
			t.start();
		}
		
		
		for(Thread t: threads) { // waiting for all threads to finish their work
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		long workingTime = System.currentTimeMillis() - startTime;
		System.out.println("System finished working. Operating time: " + workingTime);
	}
}
