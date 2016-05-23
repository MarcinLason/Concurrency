package Project;

public class PutFuture implements Future {
	private boolean isDone;

	public synchronized boolean checkIfDone() {
		return isDone;
	}

	public Object getResult() {
		return null;
	}
	
	public synchronized void markAsDone() {
		isDone = true;
		notify();
	}

	public synchronized void awaitDone() {
		while(!checkIfDone()) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
