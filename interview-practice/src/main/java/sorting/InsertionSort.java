package sorting;

import java.util.LinkedList;
import java.util.List;

/**
 * @author User
 * https://www.javatpoint.com/insertion-sort-in-java
 * 
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] arr1 = { 9, 14, 3, 2, 43, 11, 58, 22 };
		System.out.println("Before Insertion Sort");
		displayArray(arr1);
		
		insertionSort(arr1);// sorting array using insertion sort

		System.out.println("After Insertion Sort");
		displayArray(arr1);
		
		int[] arr2 = { 9, 14, 22, 3, 2, 43, 11, 11, 58, 22 };
		System.out.println("Before Insertion Sort");
		displayArray(arr2);
		
		insertionSortList(arr2);// sorting array using insertion sort

		System.out.println("After Insertion Sort");
		displayArray(arr2);
		
	}
	
	public static void insertionSort(int[] arr) {
		int n = arr.length;
		int i, j, marker, temp;
		for (i = 1; i < n; i++) {
			marker = arr[i];
			j = i - 1;
			while (j >= 0 && arr[j] > marker) {
				System.out.print("[" + i + ", " + j + "] ");
				displayArray(arr);
				// swap 
				temp = arr[j+1];
				arr[j+1] = arr[j];
				arr[j] = temp;				
				j--;							
			}	
		}		  
	}
	
	public static void displayArray(int... arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	/*
	 * Using ListArray { 9, 14, 3, 2, 43, 11, 58, 22 }
	 */
	public static void insertionSortList(int[] array) {
		List<Integer> list = new LinkedList<Integer>();
		int n = array.length;
		int j;
		list.add(array[0]);
		
		for (int i = 1; i < n; i++) {
			j = i - 1;
			while (j >= 0 && array[i] < list.get(j)) {
				j--;				
			}
			
			list.add(j+1, array[i]);
		}
		
		displayArrayList(list);
	}
	
	public static void displayArrayList(List<Integer> list) {
		for (Integer i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
