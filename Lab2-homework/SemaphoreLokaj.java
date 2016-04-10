package Lab2Home;

public class SemaphoreLokaj {
	int value;
	
	public SemaphoreLokaj(int val){
		this.value = val;
	}
	
	public synchronized void PB(int id) {
		if(value > 0) {
			value--;
		}
		else {
			while(value < 1) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			value--;
		}	
	}
		
	public synchronized void VB(int id) {
		value++;
		notifyAll();
	}
}