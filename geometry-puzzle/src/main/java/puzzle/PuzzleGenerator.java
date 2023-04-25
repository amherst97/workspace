package puzzle;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import factory.ConvexPolygonFactory;
import shape.Point;
import shape.Polygon;
import validator.ConvexPolygonValidator;
import validator.InsidePolygonValidator;

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
	private ConvexPolygonValidator convexValidator;
	private InsidePolygonValidator insideValidator;
	private Pattern pattern;

	/**
	 * @param inputStream
	 * @param display
	 * @param polygonFactory
	 * @param convexValidator
	 * @param insideValidator
	 */
	public PuzzleGenerator(InputStream inputStream, PuzzleDisplay display, ConvexPolygonFactory polygonFactory,
			ConvexPolygonValidator convexValidator, InsidePolygonValidator insideValidator) {

		this.inputStream = inputStream;
		this.display = display;
		this.polygonFactory = polygonFactory;
		this.convexValidator = convexValidator;
		this.insideValidator = insideValidator;
		pattern = Pattern.compile(REGEX);
	}

	/*
	 * Create a custom polygon based on command line input
	 */
	public void createCustomPuzzle(Scanner scanner) {
		Polygon polygon = new Polygon();
		display.promptInputPointBeforeValidShape(polygon.size() + 1);
		boolean isFinalized = false;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			try {
				if (!line.startsWith("#")) {
					// The line should be coordinates - need validate
					if (!pattern.matcher(line).matches()) {
						display.wrongInput();
						continue;
					}

					String[] tokens = line.split("\\s");
					Point point = new Point(Integer.parseInt(tokens[0].trim()), Integer.parseInt(tokens[1].trim()));

					if (isFinalized) {
						boolean isWithin = insideValidator.validate(polygon, point);
						display.promptCheckResult(isWithin, point, polygon);
					} else {
						if (polygon.hasPoint(point)) {
							display.promptInvalidPoint(point);
							display.promptIncompleteShape(polygon);
							continue;
						}

						// Append the point to the polygon
						polygon.addPoint(point);

						if (polygon.size() > 2 && !convexValidator.validate(polygon)) {
							display.promptInvalidPoint(point);
							// Need remove it as it is not able to form a convex polygon
							polygon.removeLast();
							display.promptCompleteShape(polygon);
							continue;
						}

						if (polygon.size() < 3) {
							display.promptIncompleteShape(polygon);
						} else {
							display.promptCompleteShape(polygon);
						}
					}
				} else {
					// token start with #
					if (isFinalized) {
						// exit game				
						return;
						
					} else {
						display.promptFinalShape(polygon);
						display.promptTestCoordinate();
						isFinalized = true;
					}
				}
			} catch (Exception e) {
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

				boolean isWithin = insideValidator.validate(polygon, point);
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

		}
		catch (Exception e) {
			display.wrongInput();
		}
		finally {
			scanner.close();
			display.promptGameEnd();
		}		
	}
}
