package Lab2;

public class Semafor {

	boolean value = true;
	public Semafor(boolean val){
		this.value = val;
	}
	
	
	public synchronized void P() {
		while(value == false){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		if(value == true) {
			value = false;
		}
	}
	
	public synchronized void V() {
		if ( value == true ) {
            System.out.println("You can't apply V method on an already lifted Semaphore");
        }
		value = true;
		notifyAll();
	}
}
