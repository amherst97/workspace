package Numbers;

public class VerifyPower2 {
	public static boolean isPower2(int n) {
		if (n == 0)
			return false;
 
        while (n != 1) {
            if (n % 2 != 0)
                return false;
            n = n / 2;
        }
        return true;
    }
	
	// If we subtract a power of 2 numbers by 1 then all unset bits 
	// after the only set bit become set; and the set bit become unset.
	public static boolean isPower2Bitwise(int n) {
		if (n == 0) return false;
		
		return ((n & (n - 1)) == 0) ? true : false;
	}
	
	// Take log2(n) should return an integer
	public static boolean isPower2Log(int n) {
		System.out.println(Integer.toBinaryString(n));
		float a = 3.4f;
		
		System.out.println(Math.floor(a) + " " + Math.ceil(a));
		return false;
	}

		
	public static void main(String[] args) {
		System.out.println(isPower2(-4));				
		System.out.println(isPower2Bitwise(1024));		
		System.out.println(isPower2Log(6));
		
		
		StringBuffer buf = new StringBuffer("Start");
		buf.insert(4, "le");
		System.out.println(buf);
		
		StringBuffer buf2 = buf.reverse();
		System.out.println(buf);
		System.out.println(buf2);
		
		
	}
}
