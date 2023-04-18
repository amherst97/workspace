package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhoneNumberCombination {
	
	public static final Map<String, List<String>> MAPPING = new HashMap<>();
	
	static {
		MAPPING.put("2", Arrays.asList("a", "b", "c"));
		MAPPING.put("3", Arrays.asList("d", "e", "f"));
		MAPPING.put("4", Arrays.asList("g", "h", "i"));
		MAPPING.put("5", Arrays.asList("j", "k", "l"));
		MAPPING.put("6", Arrays.asList("m", "n", "o"));
		MAPPING.put("7", Arrays.asList("p", "q", "r", "s"));
		MAPPING.put("8", Arrays.asList("t", "u", "v"));
		MAPPING.put("9", Arrays.asList("w", "x", "y", "z"));
	}
	
	public List<String> letterCombinations(String digits) {
		List<String> list = new ArrayList<String>(256);
		List<String> temp = new ArrayList<>(256);
		
		if (digits.isEmpty()) return list;
		
		for (String s : digits.split("")) {
			List<String> l = MAPPING.get(s);
			// permutation with existing list
			if (list.isEmpty()) {
				list = l.stream().collect(Collectors.toList());
			}
			else {
				temp.clear();
				for (String u : list) {
					for (String v : l) {
						temp.add(u + v);
					}
				}
				list = temp.stream().collect(Collectors.toList());
			}						
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		PhoneNumberCombination solution = new PhoneNumberCombination();
		System.out.println(solution.letterCombinations("23"));
		System.out.println(solution.letterCombinations("234"));
		System.out.println(solution.letterCombinations("6789"));
		
		IntStream.range(1, 10)
		.mapToObj(e -> "=")
		.forEach(e -> System.out.print(e));		
	}
}
