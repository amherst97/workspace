package shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon implements Geometry {
	private List<Point> coordinates;

	public Polygon() {
		coordinates = new ArrayList<Point>();
	}
	
	public Polygon(List<Point> coordinates) {
		this.coordinates = new ArrayList<Point>(coordinates);
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
	public Polygon clone() {
		return new Polygon(new ArrayList<Point>(coordinates));
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
