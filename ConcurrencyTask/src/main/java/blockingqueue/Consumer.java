package blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Consumer implements Callable<String> {
	private BlockingQueue<Integer> queue;
	
	public Consumer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	@Override
	public String call() throws InterruptedException {
		int count = 0;
	
		while (count < 50) {
			queue.take();
			count++;
		}
		
		return "Consumed " + count + " " + Thread.currentThread().getName();
	}
}
