package Lab4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.*;

public class Monitor{	
	private boolean isConsumerEmpty;
	private boolean isProducerEmpty;
	
	private Lock producerLock;
	private Lock consumerLock;
	
	private Condition firstConsumer;
	private Condition otherConsumers;
	private Condition firstProducer;
	private Condition otherProducers;
	
	private Queue<Integer> emptyElements;
	private Queue<Integer> occupiedElements;
	
	Monitor(int size){
		this.isConsumerEmpty = true;
		this.isProducerEmpty = true;
		
		this.producerLock = new ReentrantLock();
		this.consumerLock = new ReentrantLock();
		
		this.firstConsumer = consumerLock.newCondition();
		this.otherConsumers = consumerLock.newCondition();
		this.firstProducer = producerLock.newCondition();
		this.otherProducers = producerLock.newCondition();
		
		this.emptyElements = new LinkedList<Integer>();
		this.occupiedElements = new LinkedList<Integer>();
		prepareQueue(size);
	}
	
	private void prepareQueue(int size) {
		for(int i = 0; i < size; i++) {
			emptyElements.add(new Integer(i));
		}
	}
	
	
	public Integer[] startProduce(int portionSize) {
		producerLock.lock();
		while (!isProducerEmpty) {
			try {
				otherProducers.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		isProducerEmpty = false;
		
		while(emptyElements.size() < portionSize) {
            try {
                firstProducer.await();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		Integer [] myElements = new Integer[portionSize];
		for(int i = 0; i < portionSize; i++) {
			myElements[i] = emptyElements.poll();
		}
		
		producerLock.unlock();
		return myElements;	
	}
	
	public void endProduce(Integer[] portion, int id) {
		producerLock.lock();
		consumerLock.lock();
		
		for(int i = 0; i < portion.length; i++) {
			occupiedElements.add(portion[i]);
		}
		System.out.println("Producer[" + id + "] put " + portion.length + " elements into queue."); 
		isProducerEmpty = true;

        firstConsumer.signal();
        otherProducers.signal();
		consumerLock.unlock();
		producerLock.unlock();
	}
	
	public Integer[] startConsume (int portionSize) {
		consumerLock.lock();
		
		while(!isConsumerEmpty) {
			try {
				otherConsumers.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		isConsumerEmpty = false;
		
		while(portionSize > occupiedElements.size()) {
			try {
				firstConsumer.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Integer[] myElements = new Integer[portionSize];
		for(int i = 0 ; i < portionSize; i++) {
			myElements[i] = occupiedElements.poll();
		}
		consumerLock.unlock();
		return myElements;	
	}
	
	public void endConsume (Integer [] portion, int id) {
		producerLock.lock();
		consumerLock.lock();
		
		for(int i = 0; i < portion.length; i++) {
			emptyElements.add(portion[i]);
		}
		System.out.println("Consumer[" + id + "] got " + portion.length + " elements from queue."); 
		isConsumerEmpty = true;

		firstProducer.signal();
		otherConsumers.signal();
		consumerLock.unlock();
		producerLock.unlock();
	}

}