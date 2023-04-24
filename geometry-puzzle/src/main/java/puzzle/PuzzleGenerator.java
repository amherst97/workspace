package puzzle;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
	private Scanner scanner;
	private PrintStream printStream;
	
	private PuzzleDisplay display;
	private ConvexPolygonFactory polygonFactory;
	private ConvexPolygonValidator convexValidator;
	private InsidePolygonValidator insideValidator;
	
	public PuzzleGenerator(InputStream inputStream, PrintStream printStream) {
		scanner = new Scanner(inputStream);
		this.printStream = printStream;
		
		display = new PuzzleDisplay(printStream);
		polygonFactory = new ConvexPolygonFactory();
		convexValidator = new ConvexPolygonValidator();
		insideValidator = new InsidePolygonValidator();
	}
		
	public void createCustomPuzzle() {
		Polygon polygon = new Polygon();
		printStream.printf("Please enter corrdinates %d in x y format\n", polygon.size()+1);
		boolean isFinalized = false;
		
		while (scanner.hasNextLine()) {				
			String[] tokens = scanner.nextLine().split("\\s");
			
			if (!tokens[0].startsWith("#")) {
				Point point = new Point(Integer.parseInt(tokens[0].strip()), 
						Integer.parseInt(tokens[1].strip()));

				if (isFinalized) {
					display.promptFinalShape(polygon);
					boolean isWithin = insideValidator.validate(polygon, point);
					display.promptCheckResult(isWithin, point);
				}
				else {
					if (polygon.hasPoint(point)) {
						display.promptInvalidPoint(point);				
						continue;
					}						
					
					// Append the point to the polygon
					polygon.addPoint(point);			
					
					if (polygon.size() > 2 && !convexValidator.validate(polygon)) {
						display.promptInvalidPoint(point);
						// Need remove it as it is not able to form a convex polygon
						polygon.removeLast();
						continue;
					}
									
					if (polygon.size() < 3) {						
						printStream.printf("You current shape is incomplete\n");
						display.showPolygon(polygon);
						printStream.printf("Please enter corrdinates %d in x y format\n", polygon.size()+1);														
					}					
					else {
						printStream.printf("You current shape is valid and complete\n");
						display.showPolygon(polygon);		
						printStream.printf("Please enter # to finalize your shape or enter coordinates %d in x y format\n", 
								polygon.size() + 1);						
					}
				}
			}
			else {
				// token start with #
				if (isFinalized) {				
					return;
				}
				else {
					printStream.printf("Your finalized shape is\n");
					display.showPolygon(polygon);
					printStream.printf("Please key in test coordinates in x y format or enter # to quit the game\n");
					isFinalized = true;
				}										
			}
			
		}
	}
	
	public void createRandomPuzzle() {
		Polygon polygon = polygonFactory.create();
		printStream.printf("Your random shape is\n");
		display.showPolygon(polygon);
		
		printStream.printf("Please key in test coordinates in x y format or enter # to quit the game\n");
		
		while (scanner.hasNextLine()) {				
			String[] tokens = scanner.nextLine().split("\\s");
			
			if (!tokens[0].startsWith("#")) {
				Point point = new Point(Integer.parseInt(tokens[0].strip()), 
						Integer.parseInt(tokens[1].strip()));
				
				display.promptFinalShape(polygon);
				boolean isWithin = insideValidator.validate(polygon, point);
				display.promptCheckResult(isWithin, point);
			}
			else {		
				return;
			}
		}
		
	}
	
	public void createPuzzle() {
		display.promptGameStart();	
		int c = Integer.parseInt(scanner.nextLine());
	
		switch(c) {
			case 1:
				// Create custom polygon
				createCustomPuzzle();
				break;
				
			case 2:
				createRandomPuzzle();
				break;
				
			default:
				return;
		}
		
		scanner.close();
		display.promptGameEnd();
	}
	
	public static void main(String[] args) {							
		new PuzzleGenerator(System.in, System.out).createPuzzle();
	}
}
