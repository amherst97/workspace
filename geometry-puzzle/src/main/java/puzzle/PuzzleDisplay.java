package puzzle;

import java.io.PrintStream;

import shape.Point;
import shape.Polygon;
/*
 * Helper class to handle all the output to System.out so to 
 * avoid clutter the PuzzleGenerator class
 */
public class PuzzleDisplay {
	
	private PrintStream printStream;
	
	public PuzzleDisplay(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	public void showPolygon(Polygon polygon) {
		int i = 0;
		for (Point p : polygon.coordinates()) {
			printStream.printf("%d:(%d,%d)\n", ++i, p.getX(), p.getY());
		}
	}
	
	public void wrongInput() {
		printStream.printf("Please input required data and retry\n");
	}
	
	public void promptInputPointBeforeValidShape(int newSize) {
		printStream.printf("Please enter coordinates %d in x y format\n", newSize);
	}
	
	public void promptTestCoordinate() {
		printStream.printf("Please key in test coordinates in x y format or enter "
				+ "# to quit the game\n");
	}
	
	public void promptRandomShape(Polygon polygon) {
		printStream.printf("Your random shape is\n");
		showPolygon(polygon);
		printStream.printf("\n");
		promptTestCoordinate();
	}
		
	public void promptFinalShape(Polygon polygon) {
		printStream.printf("Your finalized shape is\n");
		showPolygon(polygon);
		printStream.printf("\n");
	}
 	
	public void promptNewPoint(boolean isValid, Point point, Polygon polygon) {
		if (!isValid) { 
			printStream.printf("New coordinates(%d,%d) is invalid!!!\n" +
					"Not adding new coordinates to the current shape.\n\n", point.getX(), point.getY());	
		}
		
		if (polygon.size() > 2) {
			printStream.printf("You current shape is valid and is complete\n");
			showPolygon(polygon);
			printStream.printf("Please enter # to finalize your shape or enter coordinates %d in x y format\n", 
					polygon.size() + 1);		
		}
		else {
			printStream.printf("You current shape is incomplete\n");
			showPolygon(polygon);
			printStream.printf("Please enter coordinates %d in x y format\n", polygon.size() + 1);
		}
	}
	
	public void promptCheckResult(boolean isWithin, Point point, Polygon polygon) {		
		promptFinalShape(polygon);
		if (isWithin) 
			printStream.printf("Coordinates (%d,%d) is within your finalized shape\n", point.getX(), point.getY());			
		else
			printStream.printf("Sorry, coordinates (%d,%d) is outside of your finalized shape\n", point.getX(), point.getY()) ;		
		
		promptTestCoordinate();
	}
	
	public void promptGameEnd() {
		printStream.printf("Thank you for playing the GIC geometry puzzle app \n" 
		+ "Have a nice day!\n");
				
	}

	public void promptGameStart() {
		printStream.printf("Welcome to the GIC geometry puzzle app\n"
				+ "[1] Create a custom shape\n"
				+ "[2] Generator a random shape\n");
	}
		
}
