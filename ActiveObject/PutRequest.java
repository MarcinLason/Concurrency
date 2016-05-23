package Project;

public class PutRequest implements MethodRequest{
	private Integer [] elementsToPut;
	private PutFuture result;
	private Servant executor;
	
	PutRequest(Integer[] elements, PutFuture result, Servant executor) {
		this.elementsToPut = elements;
		this.result = result;
		this.executor = executor;
	}

	@Override
	public boolean canBeExecuted() {
		int freeSpace = executor.getMaxSize() - executor.getCurrentSize();
		if (freeSpace >= elementsToPut.length) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		executor.put(elementsToPut);
		result.markAsDone();
	}

}
