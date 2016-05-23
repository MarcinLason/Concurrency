package Project;

public class GetRequest implements MethodRequest {
	
	private GetFuture result;
	private int numberOfElements;
	private Servant executor;
	
	public GetRequest(int numberOfElements, GetFuture result, Servant executor){
		this.result = result;
		this.numberOfElements = numberOfElements;
		this.executor = executor;
	}


	@Override
	public boolean canBeExecuted() {
		if(executor.getCurrentSize() >= numberOfElements) {
			return true;
		}
		return false;
	}


	@Override
	public void execute() {
		result.setResult(executor.get(numberOfElements));	
	}

}
