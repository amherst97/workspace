package sorting;

import org.junit.Assert;
import org.junit.Test;

public class MergeArrayTest {
	MergeArray mergeArray = new MergeArray();
	
	@Test
	public void testMerge() {
		int[] a = {1, 2, 3, 5, 6};
		int[] b = {2, 4, 5};
		int[] c = mergeArray.merge(a, b);
		
		for (int i : c) {
			System.out.print(i + " ");
		}
		
		Assert.assertArrayEquals(new int[]{1,2,2,3,4,5,5,6}, c);
	}
}
