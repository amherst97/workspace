package sorting;

public class MergeArray {
	
	public int[] merge(int[] a, int[] b) {		
		int i = 0;
		int j = 0;
		int index = 0;
		int[] c = new int[a.length + b.length];
		
		System.out.println("Merge a and b");
		
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				c[index] = a[i];
				i++;
			}
			else {
				c[index] = b[j];
				j++;
			}
			index++;
		}
		
		while (i < a.length) {
			c[index++] = a[i++];
		}
		
		while (j < b.length) {
			c[index++] = b[j++];
		}
		
		return c;
	}
	
}
