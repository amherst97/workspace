package validator;

import shape.Point;
import shape.Polygon;

public interface Validator {
	boolean validate(Polygon polygon, Point point);
}
