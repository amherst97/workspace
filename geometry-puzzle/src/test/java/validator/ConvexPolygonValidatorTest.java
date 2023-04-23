package validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import shape.MultiPoint;
import shape.Point;

class ConvexPolygonValidatorTest {

	ConvexPolygonValidator validator = new ConvexPolygonValidator();

	@Test
	void testConvex() {
		Point[] cooridnates = {new Point(1, 1), new Point(6, 2), new Point(7, 4), 
				new Point(5, 6), new Point(3, 6), new Point(0, 4)};
		MultiPoint polygon = new MultiPoint(Arrays.asList(cooridnates));
		
		assertTrue(validator.validate(polygon));
	}
	
	@Test
	void testNonConvex() {
		Point[] cooridnates = {new Point(0, 0), new Point(5, 0), new Point(6, 7), new Point(2, 3), new Point(0, 4)};
		MultiPoint polygon = new MultiPoint(Arrays.asList(cooridnates));
		
		assertFalse(validator.validate(polygon));
	}
}
