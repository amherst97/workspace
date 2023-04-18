package barriers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierTest {

	
	public static void main(String[] args) {
		
		class Friend implements Callable<String> {
			private CyclicBarrier barrier;
			Random rand = new Random();
			
			public Friend(CyclicBarrier barrier) {
				this.barrier = barrier;
			}
			
			public String call() throws InterruptedException, BrokenBarrierException, TimeoutException {
				String name = Thread.currentThread().getName();
				TimeUnit.SECONDS.sleep(rand.nextInt(3));
				System.out.println(name + " has arrivaed at cafe");
					
				barrier.await();
				//barrier.await(1, TimeUnit.SECONDS);
				//System.out.println(name + " let's go!");
				return name + " let's go to cinema!";
			}
		}
		
		CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Barrier opens"));
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Future<String>> futures = new ArrayList<>();
		
		try {
			for (int i = 0; i < 4; i++) {
				futures.add(executor.submit(new Friend(barrier)));
				
			}
			
			futures.forEach(f -> {
				try {
					System.out.println(f.get());
					//System.out.println(f.get(200, TimeUnit.MILLISECONDS));
				}
				catch (InterruptedException | ExecutionException e) {
					System.out.println(e);
				}
			});
		}
		finally {
			executor.shutdown();
		}
	}
}
