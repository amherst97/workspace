package validator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shape.Point;
import shape.Polygon;

class InsidePolygonValidatorTest {
	
	private Point[] cooridnates = {new Point(0, 0), new Point(5, 0), new Point(6, 7), new Point(2, 3)};
	private Polygon polygon;
	private InsidePolygonValidator validator;
	
	@BeforeEach
	public void setUp() {
		polygon = new Polygon(Arrays.asList(cooridnates));
		validator = new InsidePolygonValidator();
	}
	
	@Test
	public void testInsidePolygon() {
		Point p = new Point(2, 1);		
		assertTrue(validator.validate(polygon, p));
	}
	
	@Test
	public void testOutsidePolygon() {
		Point p = new Point(6, 2);
		assertFalse(validator.validate(polygon, p));
	}
	
	@Test
	public void testOnEdgeOfPolygon() {
		Point p = new Point(3, 4);
		assertFalse(validator.validate(polygon, p));
	}
	
	@Test void testOnExistingPoint() {
		Point p = new Point(6, 7);
		assertFalse(validator.validate(polygon, p));
	}

}
