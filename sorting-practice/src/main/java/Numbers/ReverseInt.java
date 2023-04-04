package Numbers;

public class ReverseInt {
	
	// not working trailing 0 , and bad performance
	public int reverse1(int x) {	
		String s = String.valueOf(Math.abs(x));
		
		StringBuffer buf = new StringBuffer(s);
		
		if (x >= 0) {
			return Integer.valueOf(buf.reverse().toString());
		}
		else {
			return -1 * Integer.valueOf(buf.reverse().toString());
		}
		
	}
	
	// not working, trailing 0
	public int reverse2(int x) {
		int r = 0;
		int output = 0;
		int abs_x = Math.abs(x);
		int p = (int) Math.floor(Math.log10(abs_x));
		
		while (abs_x > 0) {
			r = abs_x % 10;
			abs_x = abs_x / 10;			
			//System.out.println(abs_x + " " + r);
			if (r != 0) {
				output = output + (int) (r * Math.pow(10, p--));
			}
									
		}			
		
		if (x < 0) return (-1) * output;
		return output;
	}
	
	public int reverse(int x) {
		long output = 0;
		int r = 0;
		while (x != 0) {
			r = x % 10;
			output = output * 10 + r;
			x = x / 10;						
		}
		
		if (output >= Math.pow(2, 31) - 1 || output <= Math.pow(-2, 31)) return 0;
		return (int) output;
	}
	
	public static void main(String[] args) {
		ReverseInt rInt = new ReverseInt();
		System.out.println(rInt.reverse(1234));
		System.out.println(rInt.reverse(-1234));
		System.out.println(rInt.reverse(120));		
		System.out.println(rInt.reverse(1534236469));				
	}
	
}
