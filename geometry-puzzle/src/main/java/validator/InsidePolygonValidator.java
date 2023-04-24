package validator;

import java.util.Arrays;

import shape.Polygon;
import shape.Point;

public class InsidePolygonValidator {
	public boolean validate(Polygon polygon, Point p) {
		int size = polygon.size();
		int a, b, c, d;
		
		int[] A = new int[size];
		int[] B = new int[size];
		int[] C = new int[size];
		int[] D = new int[size];
				
		for (int i = 0; i < size; i++) {
			Point p1 = polygon.get(i);
			Point p2 = polygon.get((i+1) % size);
			
			// calculate a,b,c
			a = -1 * (p2.getY() - p1.getY());
			b = p2.getX() - p1.getX();
			c = -1 * (a * p1.getX() + b * p1.getY());
			
			A[i] = a;
			B[i] = b;
			C[i] = c;
		}
		
		for (int i = 0; i < size; i++) {
			d = A[i] * p.getX() + B[i] * p.getY() + C[i];
			D[i] = d;
		}
		
		boolean allRight = Arrays.stream(D).allMatch(e -> e > 0);
		boolean allLeft = Arrays.stream(D).allMatch(e -> e < 0);
		
		return (allRight || allLeft);
	}
	
}
