package factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import shape.Polygon;
import shape.Point;

public class ConvexPolygonFactory implements GeometryFactory {
	private static final Random RAND = new Random();
	private static final int X_SIZE = 100;
	private static final int Y_SIZE = 100;
	
	public Polygon create() {
		int n = RAND.nextInt(6) + 3; // range 3 to 8 - should be in config 		
		return new Polygon(generateConvexPolygon(n, X_SIZE, Y_SIZE));
	}
	
	public List<Point> generateConvexPolygon(int size, int rangeX, int rangeY) {
        // Generate two lists of random X and Y coordinates
        List<Integer> xPool = new ArrayList<>(size);
        List<Integer> yPool = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            xPool.add(RAND.nextInt(100));
            yPool.add(RAND.nextInt(100));
        }

        // Sort them
        Collections.sort(xPool);
        Collections.sort(yPool);

        // Isolate the extreme points
        Integer minX = xPool.get(0);
        Integer maxX = xPool.get(size - 1);
        Integer minY = yPool.get(0);
        Integer maxY = yPool.get(size - 1);

        // Divide the interior points into two chains & Extract the vector components
        List<Integer> xVec = new ArrayList<>(size);
        List<Integer> yVec = new ArrayList<>(size);

        int lastTop = minX, lastBot = minX;

        for (int i = 1; i < size - 1; i++) {
            int x = xPool.get(i);

            if (RAND.nextBoolean()) {
                xVec.add(x - lastTop);
                lastTop = x;
            } else {
                xVec.add(lastBot - x);
                lastBot = x;
            }
        }

        xVec.add(maxX - lastTop);
        xVec.add(lastBot - maxX);

        int lastLeft = minY, lastRight = minY;

        for (int i = 1; i < size - 1; i++) {
            int y = yPool.get(i);

            if (RAND.nextBoolean()) {
                yVec.add(y - lastLeft);
                lastLeft = y;
            } else {
                yVec.add(lastRight - y);
                lastRight = y;
            }
        }

        yVec.add(maxY - lastLeft);
        yVec.add(lastRight - maxY);

        // Randomly pair up the X- and Y-components
        Collections.shuffle(yVec);

        // Combine the paired up components into vectors
        List<Point> vec = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            vec.add(new Point(xVec.get(i), yVec.get(i)));
        }

        // Sort the vectors by angle
        Collections.sort(vec, Comparator.comparingDouble(v -> Math.atan2(v.getY(), v.getX())));

        // Lay them end-to-end
        int x = 0, y = 0;
        int minPolygonX = 0;
        int minPolygonY = 0;
        List<Point> points = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            points.add(new Point(x, y));

            x += vec.get(i).getX();
            y += vec.get(i).getY();

            minPolygonX = Math.min(minPolygonX, x);
            minPolygonY = Math.min(minPolygonY, y);
        }

        // Move the polygon to the original min and max coordinates
        int xShift = minX - minPolygonX;
        int yShift = minY - minPolygonY;

        for (int i = 0; i < size; i++) {
            Point p = points.get(i);
            points.set(i, new Point(p.getX() + xShift, p.getY() + yShift));
        }

        return points;
    }
}
