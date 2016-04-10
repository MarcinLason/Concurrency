package Lab3;

public class Consument implements Runnable {

	Monitor monitor;
	
	public Consument(Monitor m) {
		this.monitor = m;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			int a = monitor.pobierz();
			System.out.println(i + ") Consument: i got value " + a + ".");
		}
	}

}
