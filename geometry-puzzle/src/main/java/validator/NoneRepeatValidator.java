package validator;

import shape.Point;
import shape.Polygon;

public class NoneRepeatValidator implements Validator {
	@Override
	public boolean validate(Polygon polygon, Point point) {
		// TODO Auto-generated method stub
		return !polygon.hasPoint(point);
	}
}
