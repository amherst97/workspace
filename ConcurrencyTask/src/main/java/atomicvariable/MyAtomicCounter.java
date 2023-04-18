package atomicvariable;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;


public class MyAtomicCounter extends AtomicInteger {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1560990464662304195L;
	private static Unsafe unsafe = null;
	
	static {
		Field unsafeField;
		
		try {
			unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeField.setAccessible(true);
			unsafe = (Unsafe) unsafeField.get(null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AtomicInteger countIncrement = new AtomicInteger(0);
	
	public MyAtomicCounter(int counter) {
		super(counter);
	}
	
	public int myIncrementAndGet() {
		long valueOffset = 0L;
		
		try {
			valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
			
		}
		catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		int v;
		do {
			v = unsafe.getIntVolatile(this, valueOffset);
			countIncrement.incrementAndGet();
		} while (!unsafe.compareAndSwapInt(this, valueOffset, v, v+1));
		
		return v;
	}
	
	public int getIncrements() {
		return this.countIncrement.get();
	}
}
