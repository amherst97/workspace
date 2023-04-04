/*
 * You are given two positive integer arrays spells and potions, 
 * of length n and m respectively, where spells[i] represents 
 * the strength of the ith spell and potions[j] represents the 
 * strength of the jth potion.
 */
package Numbers;

import java.util.Arrays;

public class SuccessPair {
	 
	public int[] successfulPairs(int[] spells, int[] potions, long success) {
		int SIZE = 10;
		
		int[] postfix = new int[SIZE];
		
        for(int potion: potions) postfix[potion]  = 1;
        for(int i=SIZE - 2; i>=0; --i) postfix[i] = postfix[i] + postfix[i+1];

        for(int i=0; i<spells.length; ++i){
            long val = success / (long) spells[i];
            if(success % (long) spells[i] != 0) val++;

            if(val <= SIZE - 1) 
            	spells[i] = postfix[(int) val];
            else 
            	spells[i] = 0;
        }
        return spells;
	}
	
	
	public static void main(String[] args) {
		SuccessPair pairs = new SuccessPair();
		int[] output = pairs.successfulPairs(new int[] {5, 1, 3}, new int[] {1, 2, 3, 4, 5}, 7);
		Arrays.stream(output).forEach(e -> System.out.println(e));	
	}
}
