package word;

public class Palindrome {
	public boolean check(String s) {
		s = s.replace(" ", "").toLowerCase();
		
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			System.out.printf("%d %s %d %s\n", i, s.charAt(i), j, s.charAt(j));
			if (s.charAt(i) != (s.charAt(j))) return false;			
		}
		
		return true;
	}
	
	
	public static void main(String[] args) {
		Palindrome p = new Palindrome();		
		System.out.println(p.check("a man a plan a canal panama"));		
		System.out.println(p.check("Naman"));		
	}

}
