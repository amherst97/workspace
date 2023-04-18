package locks;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Producer implements Callable<String> {
	private MyBlockingQueue queue;
	Random rand = new Random();
	
	public Producer(MyBlockingQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public String call() throws InterruptedException {
		int count = 0;
				
		for (int i = 0; i < 50; i++) {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(50));
			queue.put(1);	// running on thread
			count++;
		}
				
		return "Produced " + count + " " + Thread.currentThread().getName();
	}	
}
