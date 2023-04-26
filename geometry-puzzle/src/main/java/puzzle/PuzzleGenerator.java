package puzzle;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import factory.ConvexPolygonFactory;
import shape.Point;
import shape.Polygon;
import validator.Validator;

/*
 * Generates puzzles consisting of polygons with custom or random shapes, 
 * and allows the user to test if points are inside or outside the generated 
 * polygon. The class uses a ConvexPolygonFactory to create convex polygons, 
 * a ConvexPolygonValidator to validate that the polygon is convex, and 
 * an InsidePolygonValidator to validate whether a point is inside or outside 
 * the polygon.
 */
public class PuzzleGenerator {
	private static final String REGEX = "^\\d{1,3}\\s+\\d{1,3}$";
	private static final int CUSTOM = 1;
	private static final int RANDOM = 2;

	private InputStream inputStream;

	private PuzzleDisplay display;
	private ConvexPolygonFactory polygonFactory;
	private List<Validator> newPointValidators;
	private List<Validator> testPointValidators;

	private Pattern pattern;


	/**
	 * @param inputStream			
	 * @param display				
	 * @param polygonFactory		
	 * @param newPointValidators
	 * @param testPointValidators
	 */
	public PuzzleGenerator(InputStream inputStream, PuzzleDisplay display, ConvexPolygonFactory polygonFactory,
			List<Validator> newPointValidators, List<Validator> testPointValidators) {

		this.inputStream = inputStream;
		this.display = display;
		this.polygonFactory = polygonFactory;
		this.newPointValidators = newPointValidators;
		this.testPointValidators = testPointValidators;
		pattern = Pattern.compile(REGEX);
	}

	/*
	 * Create a custom polygon based on command line input
	 */
	public void createCustomPuzzle(Scanner scanner) {
		Polygon polygon = new Polygon();
		display.promptInputPointBeforeValidShape(polygon.size() + 1);
		
		boolean isFinalized = false;
		boolean isValid = false;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] tokens = line.split("\\s");
			
			try {
				if (line.startsWith("#")) {
					if (isFinalized) {				
						break;
					}
					else {
						isFinalized = true;
						display.promptFinalShape(polygon);
						display.promptTestCoordinate();
					}
				}
				else {
					// should be in (X Y) coordinate format
					if (pattern.matcher(line).matches()) {
						Point point = new Point(Integer.parseInt(tokens[0].trim()), Integer.parseInt(tokens[1].trim()));
						
						if (isFinalized) {
							isValid = testPointValidators.stream().allMatch(v -> v.validate(polygon, point) == true); 
							display.promptCheckResult(isValid, point, polygon);
						}
						else {	
							if (polygon.size() >= 2) {
								isValid = newPointValidators.stream().allMatch(v -> v.validate(polygon, point) == true);
							}
							else {
								isValid = !polygon.hasPoint(point);
							}
							if (isValid) polygon.addPoint(point);
							display.promptNewPoint(isValid, point, polygon);
						}
					}
					else {
						display.wrongInput();
					}										
				}					
			}
			catch (Exception e) {
				display.wrongInput();
			}			
		}

	}

	/*
	 * Create a random convex polygon
	 */
	public void createRandomPuzzle(Scanner scanner) {
		Polygon polygon = polygonFactory.create();
		display.promptRandomShape(polygon);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			if (!line.startsWith("#")) {
				// The line should be coordinates - need validate
				if (!pattern.matcher(line).matches()) {
					display.wrongInput();
					continue;
				}

				String[] tokens = line.split("\\s");
				Point point = new Point(Integer.parseInt(tokens[0].trim()), Integer.parseInt(tokens[1].trim()));

				boolean isWithin = testPointValidators.stream().allMatch(v -> v.validate(polygon, point) == true);
				display.promptCheckResult(isWithin, point, polygon);
			} else {
				return;
			}
		}
	}

	public void createPuzzle() {
		display.promptGameStart();
		Scanner scanner = new Scanner(inputStream);
		try {
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case CUSTOM:
				// Create custom polygon
				createCustomPuzzle(scanner);
				break;

			case RANDOM:
				createRandomPuzzle(scanner);
				break;
			default:
				display.wrongInput();
				return;
			}

		} catch (Exception e) {
			display.wrongInput();
		} finally {
			scanner.close();
			display.promptGameEnd();
		}
	}
}
