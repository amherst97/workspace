package blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BlockingQueueTest {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(50);
		List<Future<String>> futures = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(8);
		
		try {
			// run 4 producers concurrently
			for (int i = 0; i < 4; i++) {
				futures.add(executor.submit(new Producer(blockingQueue)));
			}
			
			// run 4 consumers concurrently
			for (int i = 0; i < 4; i++) {
				futures.add(executor.submit(new Consumer(blockingQueue)));
			}
		
			futures.forEach(f -> {
				try {
					System.out.println(f.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		}
		finally {
			executor.shutdown();
		}
	}
}
