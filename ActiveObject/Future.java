package Project;

public interface Future {
	
	boolean checkIfDone();
	Object getResult();
	void awaitDone();
}
