/*
 *  Find longest none repeat longest substring
 *  for example: abcabcbb -> should return 3
 *  constrain: 0 <= string length < 50000
 */
package word;

import java.util.HashSet;
import java.util.Set;

public class NoneRepeatString {
	
	public int findLongestSubString(String str) {
		int length = str.length();
		
		for (int size = str.length(); size >= 1; size--) {
			for (int start = 0; start <= length - size; start++) {
				System.out.println(str.substring(start, start + size));
				if (!hasDuplicate(str.substring(start, start + size))) {					
					return size;
				}
			}
		}
		
		return 0;
	}
	
	public boolean hasDuplicate(String str) {
		Set<Integer> s = new HashSet<>();
		str.chars().forEach(e -> s.add(e));
		return str.length() == s.size() ? false : true;
	}
	
	
	public static void main(String[] args) {
		NoneRepeatString nrs = new NoneRepeatString();
		
		System.out.println(nrs.hasDuplicate("abcabcbb"));
		
		System.out.println(nrs.findLongestSubString("pwwkew"));
	}
}
