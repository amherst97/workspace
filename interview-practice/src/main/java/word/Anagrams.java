package word;

import java.util.ArrayList;
import java.util.Arrays;

public class Anagrams {
	
	public static boolean isAnagram(String s1, String s2) {
		
		if (s1.equalsIgnoreCase(s2)) return true;
		if (s1.length() != s2.length()) return false;
				
		String[] list1 = s1.toLowerCase().split("");
		String[] list2 = s2.toLowerCase().split("");		
		
		Arrays.sort(list1);
		Arrays.sort(list2);
		
		return Arrays.equals(list1, list2);		
	}
	
	public static String[][] groupAnagram(String[] input) {
		String[][] output = new String[10][];
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>(10);
		
		
		for (int i = 0 ; i < output.length; i++) {
			if (list.isEmpty()) {
				
			}
			
			for (ArrayList<String> inner : list) {
				if (isAnagram(input[i], inner.get(0))) {
					inner.add(input[i]);
				}
			}
		}
		
		return output;
	}
	
	public static void main(String[] args) {
		System.out.println(isAnagram("tar", "rat"));
		System.out.println(isAnagram("tar", "rot"));
		System.out.println(isAnagram("listen", "Silent"));
		
		String[] input = {"tar", "rat", "listen", "silent", "binary", "brainy", "Paris", "pairs"};
		String[][] output = groupAnagram(input);
	}
	
}
