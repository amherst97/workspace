package puzzle;

import java.util.Arrays;

import factory.ConvexPolygonFactory;
import validator.ConvexPolygonValidator;
import validator.InsidePolygonValidator;
import validator.NoneLinearValidator;
import validator.NoneRepeatValidator;
import validator.Validator;

public class ApplicationRunner {
	private static final int MAX_SIDES = 8;
    private static final int MIN_SIDES = 3;
    private static final int MAX_COORDINATE = 100;

    
	public static void main(String[] args) {		
		PuzzleDisplay display = new PuzzleDisplay(System.out);
		ConvexPolygonFactory polygonFactory = new ConvexPolygonFactory(MAX_COORDINATE, MIN_SIDES, MAX_SIDES);
		
		
		Validator[] newPointValidators = {new ConvexPolygonValidator(), new NoneRepeatValidator(), new NoneLinearValidator()};
		Validator[] testPointValidator = {new InsidePolygonValidator()};
		
		new PuzzleGenerator(System.in, display, polygonFactory,
				Arrays.asList(newPointValidators), Arrays.asList(testPointValidator)).createPuzzle();
	}
}
