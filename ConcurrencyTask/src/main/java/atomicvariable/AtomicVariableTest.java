package atomicvariable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableTest {
	
	//private static int counter = 0;
	private static AtomicInteger counter = new AtomicInteger(0);	
	//private static MyAtomicCounter counter = new MyAtomicCounter(0);
	
	public void increase() {
		for (int i = 0; i < 1000; i++) {
			//counter++;
			counter.getAndIncrement();
			//counter.myIncrementAndGet();
		}
		
	}
	
	public void decrease() {
		for (int i = 0; i < 1000; i++) {
			//counter--;
			counter.decrementAndGet();
		}
	}
	
	public static void main(String args[]) {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		List<Future<?>> futures = new ArrayList<>();
		
		AtomicVariableTest test = new AtomicVariableTest();
		
		for (int i = 0; i < 4; i++) {
			futures.add(executor.submit(new Runnable() {
				public void run() {					
					test.increase();
					System.out.println(counter.get());
				}
			}));
		}
		
		for (int i = 0; i < 4; i++) {
			futures.add(executor.submit(new Runnable() {
				public void run() {
					test.decrease();
					System.out.println(counter.get());
				}
			}));
		}
		
		// wait for all of them to complete. If no thread issue
		// we should get back 0
		futures.forEach(f -> {
			try {
				f.get();
			} 
			catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		executor.shutdown();
		
		System.out.println("Counter = " + counter);
		//System.out.println("# increments = " + counter.getIncrements());
	}
}
