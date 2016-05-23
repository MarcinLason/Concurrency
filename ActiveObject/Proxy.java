package Project;


public class Proxy {
	Scheduler scheduler;
	Servant servant;
	
	public Proxy(int size) {
		this.scheduler = new Scheduler();
		scheduler.setDaemon(true);
		this.servant = new Servant(size);
		scheduler.start();
	}
	
	
	public Future putPortion(Integer[] elements) {
		PutFuture result = new PutFuture();
		scheduler.enqueue(new PutRequest(elements, result, servant));
		return result;
		
	}
	
	public Future getPortion(int numberOfElements) {
		GetFuture result = new GetFuture();
		scheduler.enqueue(new GetRequest(numberOfElements, result, servant));
		return result;
	}
}
