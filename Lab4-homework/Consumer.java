package ConsProdsProject;

public class Consumer implements Runnable {
	private int id;
	private Monitor monitor;
	private int maxPortionSize;
	
	public Consumer(int id, Monitor monitor, int bufferSize) {
		this.id = id;
		this.monitor = monitor;
		this.maxPortionSize = bufferSize/2;
	}
	

	@Override
	public void run() {
		int i = 0;
		while (i < 10) {
			int portionSize = (i*17)%maxPortionSize + 1;
			//int portionSize = 200;
			monitor.get(portionSize);
			System.out.println("Consumer [" + id + "] got " + portionSize +" numbers from buffor!");
			i++;
		}

	}

}
