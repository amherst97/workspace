package locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LockTestRun {
	
	public static List<Integer> BUFFER = new ArrayList<>(50);
	
	public static void main(String[] args) throws InterruptedException {
		List<Callable<String>> consumerAndProducers = new ArrayList<>();
		
		MyBlockingQueue queue = new MyBlockingQueue();
		
		//ExecutorService executor = Executors.newFixedThreadPool(4);			
		ExecutorService executor = Executors.newCachedThreadPool();
				
		for (int i = 0; i < 3; i++) {
			consumerAndProducers.add(new Consumer(queue));
		}
		
		for (int i = 0; i < 5; i++) {
			consumerAndProducers.add(new Producer(queue));
		}
		
		try {
			
			List<Future<String>> result = executor.invokeAll(consumerAndProducers);
			
			
			result.forEach(f -> {
				try {
					System.out.println(f.get());					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} 
		finally {
			TimeUnit.SECONDS.sleep(5);
			executor.shutdown();
		}
		
	}
}
