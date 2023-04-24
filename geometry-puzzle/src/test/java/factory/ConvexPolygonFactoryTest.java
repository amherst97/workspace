package factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shape.Polygon;
import validator.ConvexPolygonValidator;

class ConvexPolygonFactoryTest {
	private ConvexPolygonFactory convexPolygonFactory;
	private ConvexPolygonValidator convexValidator;
	
	@BeforeEach
	public void setUp() {
		convexPolygonFactory = new ConvexPolygonFactory();
		convexValidator = new ConvexPolygonValidator();		
	}

	@Test
	void testRandomConvexPolygon() {		
		Polygon polygon = convexPolygonFactory.create();	
		assertTrue(convexValidator.validate(polygon));
	}
	
	@Test
	void testSizeWithinRange() {
		Polygon polygon = convexPolygonFactory.create();
		assertTrue(polygon.size() >= 3 && polygon.size() <= 8);
	}

}
