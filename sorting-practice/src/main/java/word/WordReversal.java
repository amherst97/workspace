package word;

public class WordReversal {
	
	public static String reversal(String input) {
		String[] tokens = input.split(" ");
		 
		StringBuffer output = new StringBuffer();
		
		for (int i = tokens.length - 1; i >= 0; i--) {
			output.append(tokens[i]);
			if (i > 0) output.append(" ");
		}
		
		return output.toString();
	}
	
	
	public static void main(String[] args) {
		String input = "Dog bites man";		
		System.out.println("[" + reversal(input) + "]");
		
		String input2 = "Dog bites   man";		
		System.out.println("[" + reversal(input2) + "]");
		
		String input3 = "Single";		
		System.out.println("[" + reversal(input3) + "]");
		
	}

}
