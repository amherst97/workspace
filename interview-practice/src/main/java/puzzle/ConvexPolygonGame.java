package puzzle;

import java.util.List;
import java.util.Scanner;

public class ConvexPolygonGame {
	private static final int MIN_COORDINATE = 0;
	private static final int MAX_COORDINATE = 10;

	private List<Point> vertices;

	public ConvexPolygonGame() {
		//generateRandomConvexPolygon();
		
		vertices = ValtrAlgorithm.generateRandomConvexPolygon(4);
		
		vertices.stream().forEach(e -> System.out.printf("(%d,%d) ", e.getX(), e.getY()));
	}

//	private void generateRandomConvexPolygon() {
//		Random random = new Random();
//		int n = random.nextInt(10) + 3; // Minimum 3 vertices
//		List<Point2D.Double> points = new ArrayList<>(n);
//		int cx = random.nextInt(MAX_COORDINATE - MIN_COORDINATE + 1) + MIN_COORDINATE;
//		int cy = random.nextInt(MAX_COORDINATE - MIN_COORDINATE + 1) + MIN_COORDINATE;
//		for (int i = 0; i < n; i++) {
//			int x = random.nextInt(MAX_COORDINATE - MIN_COORDINATE + 1) + MIN_COORDINATE;
//			int y = random.nextInt(MAX_COORDINATE - MIN_COORDINATE + 1) + MIN_COORDINATE;
//			points.add(new Point2D.Double(x, y));
//		}
//		Collections.sort(points, new Comparator<Point2D>() {
//			@Override
//			public int compare(Point2D p1, Point2D p2) {
//				double angle1 = Math.atan2(p1.getY() - cy, p1.getX() - cx);
//				double angle2 = Math.atan2(p2.getY() - cy, p2.getX() - cx);
//				if (angle1 < angle2) {
//					return -1;
//				} else if (angle1 > angle2) {
//					return 1;
//				} else {
//					return 0;
//				}
//			}
//		});
//		vertices = Collections.unmodifiableList(points);
//		
//		System.out.println(vertices);
//	}

	public boolean isPointInsidePolygon(int x, int y) {
//		Point2D point = new Point2D.Double(x, y);
//		Line2D ray = new Line2D.Double(point, new Point2D.Double(MAX_COORDINATE, y));
//		int intersections = 0;
//		for (int i = 0; i < vertices.size(); i++) {
//			Point2D p1 = vertices.get(i);
//			Point2D p2 = vertices.get((i + 1) % vertices.size());
//			if (ray.intersectsLine(new Line2D.Double(p1, p2))) {
//				intersections++;
//			}
//		}
//		return (intersections % 2 == 1);
		
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ConvexPolygonGame game = new ConvexPolygonGame();
		System.out.println("Welcome to the Convex Polygon Game!");
		System.out.println("A random convex polygon has been generated.");
		
		System.out.println("Please enter the x and y coordinates of a point to check if it is inside the polygon.");
		System.out.print("x = ");
		int x = scanner.nextInt();
		System.out.print("y = ");
		int y = scanner.nextInt();
		boolean inside = game.isPointInsidePolygon(x, y);
		if (inside) {
			System.out.println("The point (" + x + ", " + y + ") is inside the polygon.");
		} else {
			System.out.println("The point (" + x + ", " + y + ") is outside the polygon.");
		}
	}
}
