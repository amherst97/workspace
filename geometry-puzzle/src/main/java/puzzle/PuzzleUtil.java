package puzzle;

import shape.MultiPoint;
import shape.Point;

public class PuzzleUtil {
	public static void promptNewCoordinate() {
		System.out.println("Please key in test coordinates in x y format or enter "
				+ "# to quit the game");
	}
	
	public static void promptFinalShape(MultiPoint polygon) {
		System.out.println("You final shape is");
		polygon.display();
	}
	
	public static void promptInvalidPoint(Point point) {
		System.out.printf("New coordinates() is invalid!!!\n" +
				"Not adding new coordinates to the current shape.\n", point.getX(), point.getY());			
	}
 	
	public static void promptCheckResult(boolean isWithin, Point point) {		
		if (isWithin) 
			System.out.printf("\nCoordinates (%d,%d) is within your finalzed shape\n", point.getX(), point.getY());			
		else
			System.out.printf("\nSorry, coordinates (%d,%d) is outside of your finalized shape\n", point.getX(), point.getY()) ;		
		
		promptNewCoordinate();
	}
	
	public static void promptGameEnd() {
		System.out.println("Thank you for playing the GIC geometry puzzle app \n" 
		+ "Have a nice day!");
				
	}

	public static void promptGameStart() {
		System.out.println("Welcome to the GIC geometry puzzle app \n"
				+ "[1] Create a custom shape \n"
				+ "[2] Generator a random shape \n");
	}
		
}
