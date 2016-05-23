package Project;


public class GetFuture implements Future {

	private Integer[] result;

	public synchronized boolean checkIfDone() {
		return result != null;			
	}

	public Integer[] getResult() {
		if(result == null) {
			throw new IllegalStateException();
		}
		return result;
	}

	public synchronized void setResult(Integer[] result) {
		this.result = result;
		notify();
	}

	
	public synchronized void awaitDone() {
		while(!checkIfDone()) {
			try {
				wait();
			} catch(InterruptedException e) {
			}
		}
	}

}
