package word;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class LongestCommonPrefix {
	
	// brutal force - O(n^2)
	public String longestCommonPrefix2(String[] strs) {
		boolean found = false;
		int minLength = Arrays.stream(strs)
			.map(s -> s.length())
			.min((f, b) -> f - b)
			.orElseThrow(NoSuchElementException::new);
			
		for (int i = minLength; i >= 0; i--) {
			
			for (int j = 0; j < strs.length; j++) {
				if (strs[0].substring(0, i).equals(strs[j].substring(0, i))) {
					found = true;
				}
				else {
					found = false;
					break;
				}
			}
			
			if (found) return strs[0].substring(0, i);
		}
		
		return "";
    }
	
	// Better solution - first sort
	// then only need compare the first and last element
	// O(nLog(n))
	public String longestCommonPrefix(String[] strs) {
		int size = strs.length;
		Arrays.sort(strs);
		
		int minLength = Math.min(strs[0].length(), strs[size -1].length());

		for (int i = minLength; i >= 0; i--) {
			if (strs[0].substring(0, i).equals(strs[size-1].substring(0, i))) {
				return strs[0].substring(0, i);
			}
		}
		
		return "";
    }
	
	public static void main(String[] args) {		
		LongestCommonPrefix solution = new LongestCommonPrefix();
							
		System.out.println(solution.longestCommonPrefix(new String[] {"flower","flow","flight"}));		
		System.out.println(solution.longestCommonPrefix(new String[] {"dog","racecar","car"}));		
		System.out.println(solution.longestCommonPrefix(new String[] {""}));
	}
}
