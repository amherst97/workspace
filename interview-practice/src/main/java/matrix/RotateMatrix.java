package matrix;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RotateMatrix {
	
	// Implement the naive way, without in-place
	public void rotate_notInPlace(int[][] matrix) {
		int size = matrix.length;
		int[][] temp = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp[j][size-i-1] = matrix[i][j];
			}
		}		
		
		//display(temp);				
		for (int i = 0; i < size; i++) {
			matrix[i] = Arrays.copyOf(temp[i], temp[i].length);		
		}
	}
	
	
	public void rotate(int[][] matrix) {
		int N = matrix.length;
		for (int x = 0; x < N/ 2; x++) {
            // Consider elements in group
            // of 4 in current square
            for (int y = x; y < N - x - 1; y++) {
                // Store current cell in
                // temp variable
                int temp = matrix[x][y];
                
                // top
                matrix[x][y] = matrix[N-1-y][x];
 
                // left
                matrix[N-1-y][x] = matrix[N-1-y][N-1-x];

                matrix[N-1-y][N-1-x] = matrix[x][N-1-y];
                  
                matrix[x][N-1-y] = temp;
            }
        }
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
		//int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
		RotateMatrix rm = new RotateMatrix();		

		display(matrix);		
		rm.rotate(matrix);		
		display(matrix);
		
		Executor exe = Executors.newCachedThreadPool();
	}
	
	
	public static void display(int[][] matrix) {
		System.out.println("---------------------");
		for (int[] r : matrix) {
			for (int e : r) {
				System.out.print(e + " ");
			}
			
			System.out.println();
		}		
		
	}
}
