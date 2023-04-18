package synchronization;

import java.util.concurrent.Callable;

public class Producer implements Callable<String> {
	
	@Override
	public String call() {
		int count = 0;
		synchronized(TestRun.BUFFER) {
			while (TestRun.BUFFER.size() < 50) {
				TestRun.BUFFER.add(1);
				count++;
			}
		}
		
		return "Produced " + count + " " + Thread.currentThread().getName();
	}
}
