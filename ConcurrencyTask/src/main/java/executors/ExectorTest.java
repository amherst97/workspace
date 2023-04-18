package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExectorTest {
	
	public static void main(String[] args) {
		//Executor executor = Executors.newSingleThreadExecutor();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		try {
			Runnable task1 = () -> someLongProcess(5);
			Runnable task2 = () -> someLongProcess(10);
			
			executor.submit(task1);
			executor.submit(task2);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		executor.shutdown();
	}
	
	public static void someLongProcess(long seconds) {
		try {
			System.out.println("Long running process starts - " + Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep(seconds);
			System.out.println("Long running process ends - " + Thread.currentThread().getName());
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
