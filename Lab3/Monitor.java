package Lab3;

public class Monitor {
	private int value;
	private boolean isSet;
	
	public Monitor() {
		isSet = false;
	}
	
	public synchronized int pobierz () {
		while(isSet == false) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		isSet = false;
		notifyAll();
		return value;
		
	}
	
	public synchronized void wstaw(int x) {
		while(isSet == true) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		isSet = true;
		notifyAll();
		value = x;		
	}

}
