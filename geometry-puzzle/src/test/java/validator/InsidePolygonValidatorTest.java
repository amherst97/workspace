package validator;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shape.MultiPoint;
import shape.Point;

class InsidePolygonValidatorTest {
	
	private Point[] cooridnates = {new Point(0, 0), new Point(5, 0), new Point(6, 7), new Point(2, 3)};
	private MultiPoint polygon;
	private InsidePolygonValidator validator;
	
	@BeforeEach
	public void setUp() {
		polygon = new MultiPoint(Arrays.asList(cooridnates));
		validator = new InsidePolygonValidator();
	}
	
	@Test
	public void testInsidePolygon() {
		Point p = new Point(2, 1);		
		Assertions.assertTrue(validator.validate(polygon, p));
	}
	
	@Test
	public void testOutsidePolygon() {
		Point p = new Point(6, 2);
		Assertions.assertFalse(validator.validate(polygon, p));
	}
	
	@Test
	public void testOnEdgeOfPolygon() {
		Point p = new Point(3, 4);
		Assertions.assertFalse(validator.validate(polygon, p));
	}
	
	@Test void testOnExistingPoint() {
		Point p = new Point(6, 7);
		Assertions.assertFalse(validator.validate(polygon, p));
	}

}
