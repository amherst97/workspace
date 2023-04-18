package sorting;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr1 = { 9, 14, 3, 2, 43, 11, 58, 22 };
		System.out.println("Before Bubble Sort");
		displayArray(arr1);

		bubbleSort(arr1);// sorting array using insertion sort

		System.out.println("After Bubble Sort");
		displayArray(arr1);
	}
	
	public static void bubbleSort(int[] arr) {
		int n = arr.length;
		int temp;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n-i-1; j++) {
				if (arr[j] > arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				
				System.out.print("[" + i + ", " + j + "] ");
				displayArray(arr);
			}
		}
		
	}
	
	public static void displayArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
