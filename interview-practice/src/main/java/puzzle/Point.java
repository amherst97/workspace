package puzzle;

import java.util.Arrays;
import java.util.List;

public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public void display() {
		System.out.printf("(%d, %d)", x, y);
	}


	public List<Point> coordinates() {
		return Arrays.asList(this);
	}


	public int size() {
		return 1;
	}


	public Point get(int index) {
		return this;
	}


	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p2d = (Point) obj;
			return (getX() == p2d.getX()) && (getY() == p2d.getY());
		}
		return super.equals(obj);
	}


	public int hashCode() {
		return x * 31 + y;
	}
}
