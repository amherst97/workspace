package shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiPoint implements Geometry {
	private List<Point> coordinates;

	public MultiPoint() {
		coordinates = new ArrayList<Point>();
	}
	
	public MultiPoint(List<Point> coordinates) {
		this.coordinates = new ArrayList<Point>(coordinates);
	}
	
	@Override
	public void display() {
		int i = 0;
		for (Point p : coordinates) {
			System.out.printf("%d:(%d, %d)\n", ++i, p.getX(), p.getY());
		}
	}

	@Override
	public List<Point> coordinates() {
		return Collections.unmodifiableList(coordinates);
	}
	
	@Override
	public Point get(int index) {
		return coordinates.get(index);
	}
	
	@Override
	public int size() {
		return coordinates.size();
	}
	
	@Override 
	public MultiPoint clone() {
		return new MultiPoint(new ArrayList<Point>(coordinates));
	}

	public boolean addPoint(Point p) {
		return coordinates.add(p);
	}
	

	public boolean hasPoint(Point p) {
		return coordinates.contains(p);
	}
	
	public Point removeLast() {
		return coordinates.remove(size() -1);
	}
 	
}
