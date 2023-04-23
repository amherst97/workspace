package validator;

import shape.MultiPoint;

@FunctionalInterface
public interface ShapeValidator {
	boolean validate(final MultiPoint shape);
}
