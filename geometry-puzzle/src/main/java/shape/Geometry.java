package shape;

import java.util.List;

public interface Geometry {
	List<Point> coordinates(); 
	int size();
	Point get(int index);
}
