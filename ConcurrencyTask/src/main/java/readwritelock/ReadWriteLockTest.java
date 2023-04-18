package readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	// Without lock, HashMap is not thread-safe, and it will print out key has not been put in the map!
	// Regular lock will prevent thread issue but performance will suffer
	// Using ReadWrite lock to maximize performance and also keep thread safe
	
	private Map<Long, String> cache = new HashMap<>();
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	Lock readLock = readWriteLock.readLock();
	Lock writeLock = readWriteLock.writeLock();
	
	public String put(long key, String value) {
		try {
			writeLock.lock();
			return cache.put(key, value);
		}
		finally {
			writeLock.unlock();
		}
	}
	
	public String get(long key) {
		try {
			readLock.lock();
			return cache.get(key);
		}
		finally {
			readLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ReadWriteLockTest readWriteTest = new ReadWriteLockTest();
		System.out.println("Starting test...");
		
		class Worker implements Callable<String> {
			Random rand = new Random();
			
			@Override
			public String call() {
				while(true) {
					long key = rand.nextInt(1000);
					readWriteTest.put(key, Long.toString(key));
					if (readWriteTest.get(key) == null) {
						System.out.println("Key " + key + " has not been put in the map!");
					}
				}
			}
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		try {
			for (int i = 0; i < 4; i++) {
				executor.submit(new Worker());
			}
		}
		finally {
			executor.shutdown();
		}
		
	}
		
}
