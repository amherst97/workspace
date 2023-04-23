package puzzle;

import java.util.Scanner;

import factory.ConvexPolygonFactory;
import shape.MultiPoint;
import shape.Point;
import validator.ConvexPolygonValidator;
import validator.InsidePolygonValidator;

public class PuzzleGenerator {
	private ConvexPolygonFactory polygonFactory;
	private ConvexPolygonValidator convexValidator;
	private InsidePolygonValidator insideValidator;
	
	public PuzzleGenerator() {
		polygonFactory = new ConvexPolygonFactory();
		convexValidator = new ConvexPolygonValidator();
		insideValidator = new InsidePolygonValidator();
	}
		
	public void createCustomPuzzle(Scanner scanner) {
		MultiPoint polygon = new MultiPoint();
		System.out.printf("Please enter corrdinates %d in x y format\n", polygon.size()+1);
		boolean isFinalized = false;
		
		while (scanner.hasNextLine()) {				
			String[] tokens = scanner.nextLine().split("\\s");
			
			if (!tokens[0].startsWith("#")) {
				Point point = new Point(Integer.parseInt(tokens[0].strip()), 
						Integer.parseInt(tokens[1].strip()));

				if (isFinalized) {
					PuzzleUtil.promptFinalShape(polygon);
					boolean isWithin = insideValidator.validate(polygon, point);
					PuzzleUtil.promptCheckResult(isWithin, point);
				}
				else {
					if (polygon.hasPoint(point)) {
						PuzzleUtil.promptInvalidPoint(point);				
						continue;
					}						
					
					// Append the point to the polygon
					polygon.addPoint(point);			
					
					if (polygon.size() > 2 && !convexValidator.validate(polygon)) {
						PuzzleUtil.promptInvalidPoint(point);
						// Need remove it as it is not able to form a convex polygon
						polygon.removeLast();
						continue;
					}
									
					if (polygon.size() < 3) {						
						System.out.println("You current shape is incomplete");
						polygon.display();
						System.out.printf("Please enter corrdinates %d in x y format\n", polygon.size()+1);														
					}					
					else {
						System.out.println("You current shape is valid and complete");
						polygon.display();			
						System.out.printf("Please enter # to finalize your shape or enter coordinates %d in x y format\n", 
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
					System.out.println("Your finalized shape is");
					polygon.display();
					System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
					isFinalized = true;
				}										
			}
			
		}
	}
	
	public void createRandomPuzzle(Scanner scanner) {
		MultiPoint polygon = polygonFactory.create();
		System.out.println("Your random shape is");
		polygon.display();
		
		System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
		
		while (scanner.hasNextLine()) {				
			String[] tokens = scanner.nextLine().split("\\s");
			
			if (!tokens[0].startsWith("#")) {
				Point point = new Point(Integer.parseInt(tokens[0].strip()), 
						Integer.parseInt(tokens[1].strip()));
				
				PuzzleUtil.promptFinalShape(polygon);
				boolean isWithin = insideValidator.validate(polygon, point);
				PuzzleUtil.promptCheckResult(isWithin, point);
			}
			else {		
				return;
			}
		}
		
	}
	
	public void createPuzzle() {
		Scanner scanner = new Scanner(System.in);		
		int c = Integer.parseInt(scanner.nextLine());
	
		switch(c) {
			case 1:
				// Create custom polygon
				createCustomPuzzle(scanner);
				break;
				
			case 2:
				createRandomPuzzle(scanner);
				break;
				
			default:
				// TODO:
		}
		
		scanner.close();
		PuzzleUtil.promptGameEnd();
	}
	
	public static void main(String[] args) {				
		PuzzleUtil.promptGameStart();		
		new PuzzleGenerator().createPuzzle();
	}
}
