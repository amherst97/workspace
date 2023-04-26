package factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shape.Polygon;
import validator.ConvexPolygonValidator;

class ConvexPolygonFactoryTest {
	private static int MAX_COORDINATE = 100;
	private static int MIN_EDGES = 3;
	private static int MAX_EDGES = 8;
	private ConvexPolygonFactory convexPolygonFactory;
	private ConvexPolygonValidator convexValidator;
	
	@BeforeEach
	public void setUp() {
		convexPolygonFactory = new ConvexPolygonFactory(MAX_COORDINATE, MIN_EDGES, MAX_EDGES);		
		convexValidator = new ConvexPolygonValidator();		
	}

	@Test
	void testRandomConvexPolygon() {		
		Polygon polygon = convexPolygonFactory.create();	
		assertTrue(convexValidator.isConvext(polygon));
	}
	
	@Test
	void testSizeWithinRange() {
		Polygon polygon = convexPolygonFactory.create();
		assertTrue(polygon.size() >= MIN_EDGES && polygon.size() <= MAX_EDGES);
	}
	
	@Test
	void testCoordinateWithinRange() {
		Polygon polygon = convexPolygonFactory.create();
		polygon.coordinates().stream().forEach(p -> assertTrue(
				p.getX() >= 0 
				&& p.getX() <= MAX_COORDINATE 
				&& p.getY() >= 0 
				&& p.getY() <= MAX_COORDINATE));
	}
}
