package Project;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ActivationQueue {
	private boolean flag;
	private Deque<MethodRequest> producers;
	private Deque<MethodRequest> consumers;
	
	private Lock producerLock;
	private Lock consumerLock;
	
	ActivationQueue() {
		this.producers = new LinkedList<MethodRequest>();
		this.consumers = new LinkedList<MethodRequest>();
		this.flag = false;
		this.producerLock = new ReentrantLock();
		this.consumerLock = new ReentrantLock();
	}
	
	
	public void put (MethodRequest request) {
		if(request instanceof PutRequest) {
			producerLock.lock();
			producers.add(request);
			producerLock.unlock();
		}
		
		else if(request instanceof GetRequest) {
			consumerLock.lock();
			consumers.add(request);
			consumerLock.unlock();
		}
		
		else {
			throw new IllegalArgumentException(request.getClass().toString());
		}
	}
	
	public void putAgain(MethodRequest request) {
		if(request instanceof PutRequest) {
			producerLock.lock();
			producers.addFirst(request);
			producerLock.unlock();
		}
		
		else if(request instanceof GetRequest) {
			consumerLock.lock();
			consumers.addFirst(request);
			consumerLock.unlock();
		}
		
		else {
			throw new IllegalArgumentException(request.getClass().toString());
		}	
	}
	
	public MethodRequest getNext() {
		producerLock.lock();
		consumerLock.lock();
	
		if(!producers.isEmpty() && flag == false) {
			flag = true;
			MethodRequest result = producers.pollFirst();
			consumerLock.unlock();
			producerLock.unlock();
			return result;	
		}
		
		else if(!consumers.isEmpty() && flag == true) {
			flag = false;
			MethodRequest result = consumers.pollFirst();
			consumerLock.unlock();
			producerLock.unlock();
			return result;	
		}	
		
		consumerLock.unlock();
		producerLock.unlock();
		return null;
	}
	
}