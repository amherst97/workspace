package factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import shape.MultiPoint;
import validator.ConvexPolygonValidator;

class ConvexPolygonFactoryTest {

	@Test
	void testRandomConvexPolygon() {
		ConvexPolygonFactory polygonFactory = new ConvexPolygonFactory();
		MultiPoint polygon = polygonFactory.create();
		polygon.display();
		ConvexPolygonValidator validator = new ConvexPolygonValidator();
		
		assertTrue(validator.validate(polygon));
	}

}
