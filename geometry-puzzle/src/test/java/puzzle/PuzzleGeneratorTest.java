package puzzle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.ConvexPolygonFactory;
import validator.ConvexPolygonValidator;
import validator.InsidePolygonValidator;

class PuzzleGeneratorTest {
	private static final int MAX_SIDES = 8;
	private static final int MIN_SIDES = 3;
	private static final int MAX_COORDINATE = 100;

	PuzzleDisplay display;
	ConvexPolygonFactory polygonFactory;
	ConvexPolygonValidator convexValidator;
	InsidePolygonValidator insideValidator;

	@BeforeEach
	public void setUp() {
		polygonFactory = new ConvexPolygonFactory(MAX_COORDINATE, MIN_SIDES, MAX_SIDES);
		convexValidator = new ConvexPolygonValidator();
		insideValidator = new InsidePolygonValidator();
	}

	@Test
	void testCreateCustomPuzzle() {
		// Redirect standard input to a string
		System.setIn(new ByteArrayInputStream("1\n1 1\n5 1\n5 5\n#\n3 2\n#\n".getBytes()));

		// Redirect standard output to a ByteArrayOutputStream
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// Create a new PuzzleGenerator object and call the createCustomPuzzle method
		display = new PuzzleDisplay(new PrintStream(outContent));
		PuzzleGenerator puzzleGenerator = new PuzzleGenerator(System.in, display, polygonFactory,
				convexValidator, insideValidator);
		puzzleGenerator.createPuzzle();

		// Verify the program's output
		String expectedOutput = "Welcome to the GIC geometry puzzle app\n" + "[1] Create a custom shape\n"
				+ "[2] Generator a random shape\n" + "Please enter coordinates 1 in x y format\n"
				+ "You current shape is incomplete\n" + "1:(1,1)\n" + "Please enter coordinates 2 in x y format\n"
				+ "You current shape is incomplete\n" + "1:(1,1)\n" + "2:(5,1)\n"
				+ "Please enter coordinates 3 in x y format\n" + "You current shape is valid and is complete\n"
				+ "1:(1,1)\n" + "2:(5,1)\n" + "3:(5,5)\n"
				+ "Please enter # to finalize your shape or enter coordinates 4 in x y format\n"
				+ "Your finalized shape is\n" + "1:(1,1)\n" + "2:(5,1)\n" + "3:(5,5)\n\n"
				+ "Please key in test coordinates in x y format or enter # to quit the game\n" + "Your finalized shape is\n"
				+ "1:(1,1)\n" + "2:(5,1)\n" + "3:(5,5)\n" + "\n" + "Coordinates (3,2) is within your finalized shape\n"
				+ "Please key in test coordinates in x y format or enter # to quit the game\n"
				+ "Thank you for playing the GIC geometry puzzle app \n" + "Have a nice day!\n";

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	void testCreateRandomPuzzle() {
		// Redirect standard input to a string
		System.setIn(new ByteArrayInputStream("2\n#\n".getBytes()));

		// Redirect standard output to a ByteArrayOutputStream
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// Create a new PuzzleGenerator object and call the createCustomPuzzle method
		display = new PuzzleDisplay(new PrintStream(outContent));
		PuzzleGenerator puzzleGenerator = new PuzzleGenerator(System.in, display, polygonFactory,
				convexValidator, insideValidator);
		puzzleGenerator.createPuzzle();

		// Verify the program's output
		String expectedOutput = "(?s)Welcome to the GIC geometry puzzle app\n" + "\\[1\\] Create a custom shape\n"
				+ "\\[2\\] Generator a random shape\n" + ".*Your random shape is[\n\r].*"
				+ "Please key in test coordinates in x y format or enter # to quit the game\n"
				+ "Thank you for playing the GIC geometry puzzle app \n" + "Have a nice day!\n";

		assertTrue(outContent.toString().matches(expectedOutput));
	}

}
