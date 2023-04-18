package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {
	public static void main(String[] args) throws IOException {
		IntStream
			.range(1, 10)
			.forEach(e -> System.out.print(e));
		System.out.println();
		
		IntStream
		.range(1, 10)
		.skip(5)
		.forEach(e -> System.out.print(e));
		System.out.println();
		
		System.out.println(
			IntStream
				.range(1, 5)
				.sum()
		);
		
		// Stream.of, sorted and findFirst
		Stream.of("Ava", "Aneri", "Alberto")
			.sorted()
			.findFirst()
			.ifPresent(e -> System.out.println(e));
		
		// Stream from array, sort, filter and print
		String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah"};
		Arrays.stream(names)
			.filter(s -> s.startsWith("S"))
			.sorted()
			.forEach(s -> System.out.println(s));
		
		//average of square of int array
		int[] a = {2, 4, 6, 8, 10};
		Arrays.stream(a)
			.map(x -> x * x)
			.average()
			.ifPresent(System.out::println);
		
		// Stream from list, filter and print
		List<String> people = Arrays.asList("Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
		people.stream()
			.map(s -> s.toLowerCase())
			.filter(s -> s.startsWith("a"))
			.forEach(System.out::println);
			
		// Stream rows from text file, sort filter and print
		Stream<String> bands = Files.lines(Paths.get("./src/main/resources/bands.txt"));
		bands.sorted()
			.filter(r -> r.length() > 13)
			.forEach(System.out::println);
		bands.close();
		
		// stream rows from text fiel and save to list
		List<String> bands2 = Files.lines(Paths.get("./src/main/resources/bands.txt"))
				.filter(x -> x.contains("jit"))
				.collect(Collectors.toList());
		bands.close();
		bands2.forEach(x -> System.out.println(x));
		
		// stream rows from csv file and count good rows
		Stream<String> rows = Files.lines(Paths.get("./src/main/resources/data.txt"));
		long rowCount = rows.map(l -> l.split(","))
						.filter(l -> l.length > 0)
						.count();
		System.out.println(rowCount + " rows.");
		rows.close();
		
		// stream rows from csv file, store fields in HashMap
		Stream<String> rows3 = Files.lines(Paths.get("./src/main/resources/data.txt"));
		Map<String, Integer> map = new HashMap<>();
		map = rows3.map(s -> s.split(","))
			.filter(s -> s.length == 3)
			.filter(s -> Integer.parseInt(s[1]) > 15)
			.collect(Collectors.toMap(s -> s[0], s -> Integer.parseInt(s[1])));
		rows3.close();
		
		for (String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}
		
		// reduction = sum
		
	}
}
