package locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue {
	private final int capacity = 50;
	private int count = 0;
	private final List<Integer> list = new ArrayList<>(capacity);
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	public void put(int value) throws InterruptedException {
		try {
			lock.lock();
		
			while (list.size() == capacity) {
				notFull.await();			
			}
			
			TimeUnit.MILLISECONDS.sleep(5);
			list.add(value);			
			System.out.println("Adding value - size " + list.size() + ", " + Thread.currentThread().getName());
			notEmpty.signalAll();
			
		}
		finally {
			lock.unlock();
		}
	}
	
	public int take() throws InterruptedException {
		int result;
		try {
			lock.lock();
			
			while (list.size() == 0) {
				notEmpty.await();
			}
			
			TimeUnit.MILLISECONDS.sleep(5);
			result = list.remove(list.size()-1);
			count++;
			System.out.println("getting value, size " + list.size() + ", count "  + count + " " + Thread.currentThread().getName());
			notFull.signalAll();
			return result;
		}
		finally {
			lock.unlock();
		}
	}
	
	public boolean isEmpty() {		
		try {
			lock.lock();
			return list.isEmpty();
		}
		finally {
			lock.unlock();
		}
	}
	
	public int size() {
		try {
			lock.lock();
			return list.size();
		}
		finally {
			lock.unlock();
		}
	}
	
}
