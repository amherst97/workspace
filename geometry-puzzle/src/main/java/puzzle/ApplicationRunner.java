package puzzle;

import factory.ConvexPolygonFactory;
import validator.ConvexPolygonValidator;
import validator.InsidePolygonValidator;

public class ApplicationRunner {
	private static final int MAX_SIDES = 8;
    private static final int MIN_SIDES = 3;
    private static final int MAX_COORDINATE = 100;

    
	public static void main(String[] args) {		
		PuzzleDisplay display = new PuzzleDisplay(System.out);
		ConvexPolygonFactory polygonFactory = new ConvexPolygonFactory(MAX_COORDINATE, MIN_SIDES, MAX_SIDES);
		ConvexPolygonValidator convexValidator = new ConvexPolygonValidator();
		InsidePolygonValidator insideValidator = new InsidePolygonValidator();
		
		new PuzzleGenerator(System.in, display, polygonFactory, convexValidator, insideValidator).createPuzzle();
	}
}
