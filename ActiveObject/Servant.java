package Project;

import java.nio.BufferOverflowException;
import java.util.*;

public class Servant {
	private Deque<Integer> dataArray;
	private int size; //size of the dataArray list
	
	public Servant(int bufforSize) {
		this.size = bufforSize;
		this.dataArray = new ArrayDeque<Integer>(bufforSize);
	}
	
	public void put (Integer[] portion) {
		try {
		if(dataArray.size() + portion.length > size) {
			throw new BufferOverflowException();
		}} catch (BufferOverflowException e) {
			e.printStackTrace();
		}
		
		for(Integer i : portion) {
			dataArray.add(i);
		}
	}
	
	public Integer[] get (int numberOfElements) {
		try {
		if(dataArray.size() < numberOfElements) {
			throw new NullPointerException();
		}} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		Integer[] result = new Integer[numberOfElements];
		for(int i = 0; i < numberOfElements; i++) {
			result[i] = dataArray.pop();
		}
		
		return result;
	}
	
	
	public int getCurrentSize() {
		return dataArray.size();
	}
	
	public int getMaxSize() {
		return size;
	}

}
