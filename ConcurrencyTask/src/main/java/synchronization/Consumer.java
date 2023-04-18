package synchronization;

import java.util.concurrent.Callable;

// Simulate a consumer 
public class Consumer implements Callable<String> {
	
	@Override
	public String call() {
		int count = 0;
		
		synchronized(TestRun.BUFFER) {
			while (!TestRun.BUFFER.isEmpty()) {
				TestRun.BUFFER.remove(TestRun.BUFFER.size() - 1);
				count++;
			}
		}
				
		return "Consumed " + count + " " + Thread.currentThread().getName();
	}
	
}
