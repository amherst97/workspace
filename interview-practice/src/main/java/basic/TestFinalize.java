package basic;

public class TestFinalize {
	private String field = "test";
	
	public static void main(String[] args) {
		TestFinalize test = new TestFinalize();
		test.field = null;
		
		//test = null;
		System.gc();
	}

	@Override
	public void finalize() {
		// Will only be called once the object reference is set to null
		System.out.println("Calling finalize");
	}
 }
