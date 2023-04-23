package word;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    
    static boolean isAnagram(String a, String b) {
        // Complete the function
    	if (a.length() != b.length()) return false;
        Map<Character, Integer> aMap = new HashMap<>();
        Map<Character, Integer> bMap = new HashMap<>();
        
        for (int i = 0; i < a.length(); i++) {
            Character aa = a.toLowerCase().charAt(i);
            Character bb = b.toLowerCase().charAt(i);
            if (aMap.containsKey(aa)) {
            	aMap.computeIfPresent(aa,(k, v) -> {return v + 1;});
            }
            else {
                aMap.put(aa, 1);
            }    
            
            if (bMap.containsKey(bb)) {
                bMap.computeIfPresent(bb, (k, v) -> {return v + 1;});       
            }
            else {
                bMap.put(bb, 1);
            }   
        }
        
        System.out.println(aMap);
        System.out.println(bMap);
        return aMap.equals(bMap);
        
    }

  public static void main(String[] args) {
    
        String a = "anagram";
        String b = "margaaa";
        
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
        
     
        

    }
}
