package validator;

import shape.Polygon;
import shape.Point;

/*
 * Traverse the array and check if direction of cross product 
 * of any two adjacent sides of the polygon are same or not. 
 * If found to be true, it is convex polygon 
 */
public class ConvexPolygonValidator implements Validator {
	
	@Override
	public boolean validate(Polygon polygon, Point p) {
		Polygon newPolygon = new Polygon(polygon.coordinates());
		newPolygon.addPoint(p);
		return isConvext(newPolygon);
	}
	
    public boolean isConvext(Polygon polygon) {
        int size = polygon.size();
        if (size < 3) return false;

        Point[] points = new Point[size];
        int i = 0;
        for (Point p : polygon.coordinates()) {
            points[i++] = p;
        }

        // Check for collinear points
        for (i = 0; i < size; i++) {
            Point p1 = points[i];
            Point p2 = points[(i+1) % size];
            Point p3 = points[(i+2) % size];
            if (isCollinear(p1, p2, p3)) {
                return false;
            }
        }

        // Check for convexity using cross products
        Point v = new Point(points[1].getX() - points[0].getX(), points[1].getY() - points[0].getY());
        Point u = new Point(points[2].getX() - points[1].getX(), points[2].getY() - points[1].getY());
        int res = crossProduct(u, v);

        for (i = 1; i < size; i++) {
            v = u;
            u = new Point(points[(i+2) % size].getX() - points[(i+1) % size].getX(),
                          points[(i+2) % size].getY() - points[(i+1) % size].getY());
            int newres = crossProduct(u, v);
            if ((newres > 0 && res < 0) || (newres < 0 && res > 0)) {
                return false;
            }
        }
        return true;
    }

    // Returns true if the three points are collinear
    private boolean isCollinear(Point p1, Point p2, Point p3) {
        return crossProduct(new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY()),
                            new Point(p3.getX() - p2.getX(), p3.getY() - p2.getY())) == 0;
    }

    // Returns the cross product of two vectors
    private int crossProduct(Point v, Point u) {
        return u.getX() * v.getY() - u.getY() * v.getX();
    }
}
