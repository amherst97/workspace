package synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestRun {
	public static List<Integer> BUFFER = new ArrayList<>(50);
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(8);	
		List<Callable<String>> consumerAndProducers = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			consumerAndProducers.add(new Consumer());
			consumerAndProducers.add(new Producer());
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
			executor.shutdown();
		}
		
	}
}
