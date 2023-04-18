package executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableFutureTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		Callable<String> task = () -> {
			TimeUnit.MILLISECONDS.sleep(200);
			return "I am in thread " + Thread.currentThread().getName();
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		try {
			for (int i = 0; i < 10; i++) {
				Future<String> future = executor.submit(task);
				System.out.println("I get " + future.get(250, TimeUnit.MILLISECONDS));
			}
		}
		finally {
			executor.shutdown();
		}
	}
}
