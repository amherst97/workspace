package shape;

import java.util.List;

public interface Geometry {
	void display();
	List<Point> coordinates(); 
	int size();
	Point get(int index);
}
