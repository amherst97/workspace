package validator;

import shape.MultiPoint;

public class ThreePointValidator implements ShapeValidator {
	@Override
	public boolean validate(MultiPoint shape) {
		return false;
	}
}
