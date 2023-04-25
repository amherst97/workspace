package validator;

import shape.Polygon;
import shape.Point;

/*
 * 
 */
public class InsidePolygonValidator {
    public boolean validate(Polygon polygon, Point p) {
        int numVertices = polygon.size();

        // Calculate the coefficients of the line segments
        int[] a = new int[numVertices];
        int[] b = new int[numVertices];
        int[] c = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            Point p1 = polygon.get(i);
            Point p2 = polygon.get((i + 1) % numVertices);
            a[i] = p1.getY() - p2.getY();
            b[i] = p2.getX() - p1.getX();
            c[i] = p1.getX() * p2.getY() - p2.getX() * p1.getY();
        }

        // Compute the distances from the point to the line segments
        int[] d = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            d[i] = a[i] * p.getX() + b[i] * p.getY() + c[i];
        }

        // Check whether the point is inside the polygon
        boolean allRight = true;
        boolean allLeft = true;
        for (int i = 0; i < numVertices; i++) {
            if (d[i] > 0) {
                allLeft = false;
            } else if (d[i] < 0) {
                allRight = false;
            }
        }
        return (allRight || allLeft);
    }
}
