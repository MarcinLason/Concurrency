package Project;

public class Scheduler extends Thread {
	private ActivationQueue aQueue;

	
	public Scheduler() {
		this.aQueue = new ActivationQueue();
	}
	
	public void run() {
		
		while(true) {
			MethodRequest method = aQueue.getNext();
			if(method != null && method.canBeExecuted()) {
				method.execute();
			} else if (method != null) {
				enqueueAgain(method);
			}
		}
	}	
	
	public void enqueue(MethodRequest method) {
		aQueue.put(method);
	}
	
	public void enqueueAgain(MethodRequest method) {
		aQueue.putAgain(method);
	}
}
