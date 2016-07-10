import java.util.*;

/*
		- Position class
		- Generate positions
*/

class Solution {

	// MAIN
	//
	public static void main(String[] args) {

		String[] strings = generateSubstrings("books");
		for (String s : strings) {
			System.out.println(s);
		}
	}

	// GENERATE SUBSTRINGS | Return lexicographically sorted array of all
	// 										 | substrings of A.
	//
	private static String[] generateSubstrings(String A) {

		HashSet<String> substrings = new HashSet<String>();
		// empty string
		substrings.add("");
		// for each start index
		for (int i = 0; i < A.length(); i++)
			// for each end index
			for (int j = i+1; j <= A.length(); j++)
				substrings.add(A.substring(i,j));

		// convert to array & sort
		String[] strings = new String[substrings.size()];
		strings = substrings.toArray(strings);
		Arrays.sort(strings);
		return strings;
	}

	

	// POSITION | The Position class represents a game position (a,b), where
	// 					| a & b are valid substrings of the main Strings A & B
	// 					| respectively.
	//
	class Position {

		String a;
		String b;

		Position(String x, String y) {
			a = x;
			b = y;
		}
	}
}
