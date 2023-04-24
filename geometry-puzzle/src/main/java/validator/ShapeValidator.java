package validator;

import shape.Polygon;

@FunctionalInterface
public interface ShapeValidator {
	boolean validate(final Polygon shape);
}
