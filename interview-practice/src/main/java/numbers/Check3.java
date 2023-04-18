package numbers;

import java.io.IOException;
import java.util.Scanner;

@FunctionalInterface
interface PerformOperation {
	boolean check(int a);
}

public class Check3 {
	
	static PerformOperation isOdd = (a) -> {
			return (a % 2 == 1);
		};
	
	static PerformOperation isPrime = (a) -> {
		return false;
	};
	
	static PerformOperation isPalindrome = (a) -> {
		
		return false;
	};
 	
		
		
		
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(
                "[ASSIGNMENT:] "+
              "-> Lambda expressions <-\n");
        /*
        * Input an integer:
        * (the number of test cases)
        *   Press ENTER
        *
        * Input 2 space-separated integers: 
        *     The first integer specifies 
        *     the condition to check for 
        *         1 for Odd/Even
        *         2 for Prime
        *         3 for Palindrome
        *     The second integer denotes the 
        *     number to be checked
        *
        *  PRESS SUBMIT
        */
		
		PerformOperation op;
		int numOfInput = scanner.nextInt();
		
		while (numOfInput-- > 0) {
			System.out.println(scanner.delimiter());
			String s = scanner.next();
			String line[] = s.split(" ");
			
		
			
			int code = Integer.parseInt(line[0]);
			int input =  Integer.parseInt(line[1]);
			
			switch(code) {
			case 1:
				op = Check3.isOdd;
				System.out.println(op.check(input));
				break;
				
			case 2:
				break;
				
			case 3:
				break;
			}
		}
		
		
		scanner.close();
	}
	
}
