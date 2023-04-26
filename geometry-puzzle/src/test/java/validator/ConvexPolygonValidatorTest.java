package validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shape.Point;
import shape.Polygon;

class ConvexPolygonValidatorTest {

	private ConvexPolygonValidator validator;
	
	@BeforeEach
	void setUp() {
		validator = new ConvexPolygonValidator();
	}

	@Test
	void testConvex() {
		Point[] cooridnates = {new Point(1, 1), new Point(6, 2), new Point(7, 4), 
				new Point(5, 6), new Point(3, 6)};
		Polygon polygon = new Polygon(Arrays.asList(cooridnates));
		
		assertTrue(validator.validate(polygon, new Point(0, 4)));
	}
	
	@Test
	void testNonConvex() {
		Point[] cooridnates = {new Point(0, 0), new Point(5, 0), new Point(6, 7), new Point(2, 3)};
		Polygon polygon = new Polygon(Arrays.asList(cooridnates));
		
		assertFalse(validator.validate(polygon, new Point(0, 4)));
	}
}
