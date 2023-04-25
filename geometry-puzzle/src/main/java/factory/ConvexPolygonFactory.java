package factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import shape.Point;
import shape.Polygon;
/*
 * Create a random Polygon with number of edges 
 * between minEdges and maxEdges, and in a 2-D plane
 * with coordinate range between [0, maxCoordinate]
 */
public class ConvexPolygonFactory implements GeometryFactory {
	private static final Random RAND = new Random();
	private final int maxCoordinate;
	private final int minEdges;
	private final int maxEdges;
	
	/**
	 * @param coordinate range between 0 to maxCoordinate
	 * @param minimum number of edges
	 * @param maximum number of edges
	 */
	public ConvexPolygonFactory(int maxCoordinate, int minEdges, int maxEdges) {
		this.maxCoordinate = maxCoordinate;
        this.minEdges = minEdges;
        this.maxEdges = maxEdges;
	 }
	
	@Override
	public Polygon create() {
		int numOfEdges = RAND.nextInt(maxEdges - minEdges + 1) + minEdges; 	
		return new Polygon(generateConvexPoints(numOfEdges));
	}
		
	/**
	 * @param number of points being generated
	 * @return list of points for a convex polygon
	 */
	public List<Point> generateConvexPoints(int numEdges) {
        // Generate two lists of random X and Y coordinates        
        List<Integer> xPool = generateRandomList(numEdges);
        List<Integer> yPool = generateRandomList(numEdges);
        
        // Sort them
        Collections.sort(xPool);
        Collections.sort(yPool);

        // Isolate the extreme points
        Integer minX = xPool.get(0);
        Integer maxX = xPool.get(numEdges - 1);
        Integer minY = yPool.get(0);
        Integer maxY = yPool.get(numEdges - 1);

        // Divide the interior points into two chains & Extract the vector components
    	List<Integer> xVec = populateComponent(numEdges, minX, maxX, xPool);
		List<Integer> yVec = populateComponent(numEdges, minY, maxY, yPool);

        // Randomly pair up the X- and Y-components
        Collections.shuffle(yVec);

        // Combine the paired up components into vectors
        List<Point> vec = new ArrayList<>(numEdges);

        for (int i = 0; i < numEdges; i++) {
            vec.add(new Point(xVec.get(i), yVec.get(i)));
        }

        // Sort the vectors by angle
        Collections.sort(vec, Comparator.comparingDouble(v -> Math.atan2(v.getY(), v.getX())));

        // Lay them end-to-end
        int x = 0, y = 0;
        int minPolygonX = 0;
        int minPolygonY = 0;
        List<Point> points = new ArrayList<>(numEdges);

        for (int i = 0; i < numEdges; i++) {
            points.add(new Point(x, y));

            x += vec.get(i).getX();
            y += vec.get(i).getY();

            minPolygonX = Math.min(minPolygonX, x);
            minPolygonY = Math.min(minPolygonY, y);
        }

        // Move the polygon to the original min and max coordinates
        int xShift = minX - minPolygonX;
        int yShift = minY - minPolygonY;

        for (int i = 0; i < numEdges; i++) {
            Point p = points.get(i);
            points.set(i, new Point(p.getX() + xShift, p.getY() + yShift));
        }

        return points;
    }
	
	private List<Integer> populateComponent(int numEdges, int min, int max, List<Integer> pool) {
		List<Integer> vec = new ArrayList<>(numEdges);

		// int lastLeft = min, lastRight = min;
		int lastTop = min, lastBot = min;

		for (int i = 1; i < numEdges - 1; i++) {
			int x = pool.get(i);

			if (RAND.nextBoolean()) {
				vec.add(x - lastTop);
				lastTop = x;
			} 
			else {
				vec.add(lastBot - x);
				lastBot = x;
			}
		}

		vec.add(max - lastTop);
		vec.add(lastBot - max);

		return vec;
	}
	
	private List<Integer> generateRandomList(int size) {
		return RAND.ints(size, 0, maxCoordinate).boxed().collect(Collectors.toList());
		
	}
}
