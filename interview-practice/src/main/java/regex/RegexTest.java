package regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main (String[] args) {
		// String matches
		System.out.println("abcdefg".matches("c.d"));
		System.out.println("abcdefg".matches(".b.d.f."));
		
		// split with regex, the regex will remove matched string / characters
		System.out.println(Arrays.asList("This is just a test!!!".split(".")));
		
		System.out.println(Arrays.asList("aaaaa".split("a")));
		
		System.out.println(Arrays.asList("1234aaaaa".split("a")));
		
		// However, it is not true for matches in the beginning or middle
		System.out.println(Arrays.asList("aaaaa1234aaaaa34456".split("a")));
		
		System.out.println("one + one = 2".replaceAll("one", "1"));
		
		// Capture group
		Pattern pattern = Pattern.compile("(\\w+)(-(\\w+))+-(\\w+)");
		display(pattern, "securities-development-equities-valuation-asia");
		display(pattern, "fixed_income-development-equities-emea");
		display(pattern, "fx-development-americs");
		
		pattern = Pattern.compile("(\\w+-)+(\\w+)");
		display(pattern, "securities-development-equities-valuation-asia");
	}

	
	
	
	public static void display(Pattern patter, String group) {
		Matcher matcher = patter.matcher(group);
		
		int count = matcher.groupCount();
		if (matcher.matches()) {
			for (int i = 0; i <= count; i++) {
				System.out.printf("%s.%s%n", i, matcher.group(i));
			}
		}
	}
}
