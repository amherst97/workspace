package puzzle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class PuzzleGeneratorTest {
	
	@Test
    void testCreateCustomPuzzle() {
        // Redirect standard input to a string
        System.setIn(new ByteArrayInputStream("1\n1 1\n5 1\n5 5\n#\n3 2\n#\n".getBytes()));
        
        // Redirect standard output to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Create a new PuzzleGenerator object and call the createCustomPuzzle method
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator(System.in, System.out);
        puzzleGenerator.createPuzzle();
        
        // Verify the program's output
        String expectedOutput = "Welcome to the GIC geometry puzzle app\n" + 
        		"[1] Create a custom shape\n" +
        		"[2] Generator a random shape\n" + 
        		"Please enter corrdinates 1 in x y format\n" +
        	    "You current shape is incomplete\n" +
        	    "1:(1,1)\n" +
        	    "Please enter corrdinates 2 in x y format\n" +
        	    "You current shape is incomplete\n" +
        	    "1:(1,1)\n" +
        	    "2:(5,1)\n" +
        	    "Please enter corrdinates 3 in x y format\n" +
        	    "You current shape is valid and complete\n" +
        	    "1:(1,1)\n" +
        	    "2:(5,1)\n" +
        	    "3:(5,5)\n" +
        	    "Please enter # to finalize your shape or enter coordinates 4 in x y format\n" +
        	    "Your finalized shape is\n" +
        	    "1:(1,1)\n" +
        	    "2:(5,1)\n" +
        	    "3:(5,5)\n" +
        	    "Please key in test coordinates in x y format or enter # to quit the game\n" +
        	    "You final shape is\n" +
        	    "1:(1,1)\n" +
        	    "2:(5,1)\n" +
        	    "3:(5,5)\n" +
        	    "\n" +
        	    "Coordinates (3,2) is within your finalzed shape\n" +
        	    "Please key in test coordinates in x y format or enter # to quit the game\n" +
        	    "Thank you for playing the GIC geometry puzzle app \n" +
        	    "Have a nice day!\n";
	       
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
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator(System.in, System.out);
        puzzleGenerator.createPuzzle();
        
        // Verify the program's output       
        String expectedOutput = "(?s)Welcome to the GIC geometry puzzle app\n" +
        		"\\[1\\] Create a custom shape\n" +
        		"\\[2\\] Generator a random shape\n" +
        		".*Your random shape is[\n\r].*" + 
        		"Please key in test coordinates in x y format or enter # to quit the game\n" +
        	    "Thank you for playing the GIC geometry puzzle app \n" +
        	    "Have a nice day!\n";
        
        assertTrue(outContent.toString().matches(expectedOutput));
	}
	
}
