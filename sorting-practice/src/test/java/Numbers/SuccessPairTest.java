package Numbers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class SuccessPairTest {

	@Test
	public void test() throws Exception {
		SuccessPair pairs = new SuccessPair();		

		Files.lines(Paths.get("./src/test/resources/spells.txt"))
			.findFirst().ifPresent(s -> {			
				List<Integer> spells = Arrays.stream(s.split(",")).map(e -> Integer.valueOf(e))
												.collect(Collectors.toList());
				System.out.println(spells.size());
			
			});
						
		Files.lines(Paths.get("./src/test/resources/potions.txt"))
			.findFirst().ifPresent(s -> {			
				List<Integer> potions = Arrays.stream(s.split(",")).map(e -> Integer.valueOf(e))
											.collect(Collectors.toList());
				
				System.out.println(potions.size());
			
			});
		
		
	
		//int[] output = pairs.successfulPairs(spells.to, potions, 1651505078);
		//Arrays.stream(output).forEach(e -> System.out.print(e + " "));
	}

}
