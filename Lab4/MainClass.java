package Lab4;

public class MainClass {
	
	
	public static void main(String[] args) {
		int SIZE = 300;
		int tNumber = 100;
		Monitor monitor = new Monitor(SIZE);
		
		
		Producer [] p = new Producer[tNumber];
		Consumer [] c = new Consumer[tNumber];
		
		
		for(int i = 0; i < 100; i++) {
			p[i] = new Producer(monitor, i, SIZE);
			c[i] = new Consumer(monitor, i, SIZE);
			
			Thread t1 = new Thread(p[i]);
			t1.start();
			Thread t2 = new Thread(c[i]);
			t2.start();
		}	
	}
} 
