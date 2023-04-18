package locks;

import java.util.concurrent.Callable;

// Simulate a consumer 
public class Consumer implements Callable<String> {
	private MyBlockingQueue queue;
	
	public Consumer(MyBlockingQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public String call() throws InterruptedException {
		int count = 0;
		int result = 0;
		
		do {
			result = queue.take();	// running on thread
			count++;
		} while (result != -1);

		return "Consumed " + count + " " + Thread.currentThread().getName();
	}
	
}
