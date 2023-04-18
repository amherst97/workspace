package numbers;
/*
 *You are given an integer array height of length n. 
 *There are n vertical lines drawn such that 
 *the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *Find two lines that together with the x-axis form a container, 
 *such that the container contains the most water.
 *Return the maximum amount of water a container can store. 
 */
public class MaxAreaSolution {
	// correct but running time is O(n2)
	public int maxArea2(int[] height) {
		int newArea, area = 0;
		int n = height.length;
		
		for (int i = 0; i < n-1; i++) {
			for (int j = i; j < n; j++) {
				newArea = (j - i) * Math.min(height[i], height[j]);
				if (newArea > area) 
					area = newArea;
			}
		}
		
		return area;
	}
	
	// incorrect logic
	public int maxArea3(int[] height) {
		int n = height.length;
		int first = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;
		int first_index = -1;
		int second_index = -1;
		
		for (int i = 0; i < n; i++) {
			// find the first bar
			if (height[i] - i > first) {
				first = height[i] - i;
				first_index = i;
			}
			
			// find the second bar
			if (height[i] + i >= second) {
				second = height[i] + i;
				second_index = i;
			}
		}
		
		
		return (second_index - first_index) * Math.min(height[first_index], height[second_index]);
		// find the second bar
	}
	
	// correct logic with O(n) time
	// starting from both end, and move pointer if corresponding value is less than value from 
	// other pointer
	public int maxArea(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int area = (j - i) * Math.min(height[i], height[j]);
		
		while (j > i) {
			if (height[i] < height[j]) {
				i++;
			}
			else {
				j--;
			}
			
			int newArea = (j - i) * Math.min(height[i], height[j]);
			if (newArea > area) {
				area = newArea;
			}
		}
		
		return area;
	}
	
	
	public static void main(String[] args) {
		MaxAreaSolution solution = new MaxAreaSolution();
		//int[] height = {1,8,6,2,5,4,8,3,7};
		//int[] height = {1,2,1};
		int[] height = {1,2,3,4};
		System.out.print(solution.maxArea(height));
	}
}
