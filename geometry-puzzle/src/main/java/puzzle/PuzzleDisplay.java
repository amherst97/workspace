package puzzle;

import java.io.PrintStream;

import shape.Point;
import shape.Polygon;

public class PuzzleDisplay {
	
	private PrintStream printStream;
	
	public PuzzleDisplay(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	public void showPolygon(Polygon polygon) {
		int i = 0;
		for (Point p : polygon.coordinates()) {
			System.out.printf("%d:(%d,%d)\n", ++i, p.getX(), p.getY());
		}
	}
	
	public void promptNewCoordinate() {
		printStream.printf("Please key in test coordinates in x y format or enter "
				+ "# to quit the game\n");
	}
	
	public void promptFinalShape(Polygon polygon) {
		printStream.printf("You final shape is\n");
		showPolygon(polygon);
	}
	
	public void promptInvalidPoint(Point point) {
		printStream.printf("New coordinates() is invalid!!!\n" +
				"Not adding new coordinates to the current shape.\n", point.getX(), point.getY());			
	}
 	
	public void promptCheckResult(boolean isWithin, Point point) {		
		if (isWithin) 
			printStream.printf("\nCoordinates (%d,%d) is within your finalzed shape\n", point.getX(), point.getY());			
		else
			printStream.printf("\nSorry, coordinates (%d,%d) is outside of your finalized shape\n", point.getX(), point.getY()) ;		
		
		promptNewCoordinate();
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
