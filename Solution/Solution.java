import java.util.*;

/*
		- Position class
		- Generate positions
*/

public class Solution {

	// MAIN
	//
	public static void main(String[] args) {

		Game game = new Game("cat", "dog");

	}
}

// GAME | The Game class represents an instance of the Two Word Game for
// 			| two strings A & B.
//
class Game {

	String A;
	String B;

	Game(String a, String b) {
		A = a;
		B = b;
	}

	// SUBSTRINGS | Return lexicographically sorted array of all
	//					  | substrings of A.
	//
	String[] substrings(String A) {

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

	// START POSITIONS | Return lexicographically sorted array of all start
	// 								 | positions for strings A & B.
	//
	Position[] startPositions() {

		String[] subsA = substrings(A);
		String[] subsB = substrings(B);
		Position[] positions = new Position[subsA.length * subsB.length];
		int i = 0;
		// for each substring of A
		for (String a : subsA) {
			// for each substring of B
			for (String b : subsB) {
				positions[i] = new Position(a, b);
				i++;
			}
		}
		return positions;
	}

	void printPositions() {
		Position[] pos = startPositions();
		for (Position p : pos) {
			System.out.println(p);
		}
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

		// TO STRING | Return string representation.
		//
		public String toString() {
			return String.format("(%s, %s)", a, b);
		}
	}
}
