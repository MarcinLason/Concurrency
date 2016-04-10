package Lab2Home;

public class BinarySemaphore {
	boolean value;
	
	public BinarySemaphore(boolean val){
		this.value = val;
	}
		
	public synchronized void P() {
		if(value == true) {
			value = false;
		}
		else{
			while(value == false){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			value = false;
		}
		
			
		
	}
		
	public synchronized void V() {
		value = true;
		notifyAll();
		}
}
