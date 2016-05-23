package ConsProdsProject;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	private final int size;
	private boolean firstConsumerEmpty;
	private boolean firstProducerEmpty;
	private Deque<Integer> dataArray;
	private Lock lock;
	
	private Condition firstConsumer;
	private Condition firstProducer;
	private Condition otherConsumers;
	private Condition otherProducers;
	
	
	public Monitor(int size) {
		this.size = size;
		this.dataArray = new ArrayDeque<Integer>(size);
		this.lock = new ReentrantLock();
		this.firstConsumerEmpty = true;
		this.firstProducerEmpty = true;
		this.firstConsumer = lock.newCondition();
		this.firstProducer = lock.newCondition();
		this.otherConsumers = lock.newCondition();
		this.otherProducers = lock.newCondition();
	}
	
	public Integer[] get(int portionSize) {
		lock.lock();
		
		while(!firstConsumerEmpty) {
			try {
				otherConsumers.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		firstConsumerEmpty = false;
		
		while(dataArray.size() < portionSize) {
			try {
				firstConsumer.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Integer [] result = new Integer[portionSize];
		for(int i = 0; i < portionSize; i++) {
			result[i] = dataArray.poll();
		}
		
		firstConsumerEmpty = true;
		otherConsumers.signal();
		firstProducer.signal();
		lock.unlock();
		return result;	
	}
	
	public void put (Integer[] portion) {
		lock.lock();
		
		while(!firstProducerEmpty) {
			try {
				otherProducers.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		firstProducerEmpty = false;
		
		while(size < dataArray.size() + portion.length) {
			try {
				firstProducer.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < portion.length; i++) {
			dataArray.add(portion[i]);
		}
		
		firstProducerEmpty = true;
		otherProducers.signal();
		firstConsumer.signal();
		
		lock.unlock();		
	}
}
