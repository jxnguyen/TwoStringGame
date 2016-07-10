import java.util.*;

/*
		- isWinningPosition
*/

public class Solution {

	// MAIN
	//
	public static void main(String[] args) {

		Game game = new Game("cat", "dog");
		// game.printPositions();
		game.solve();
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

	public void solve() {
		System.out.println(isWinningPosition(new Position("cat", "dog")));
	}

	// SUBSTRINGS | Return lexicographically sorted array of all
	//					  | substrings of A.
	//
	private String[] substrings(String A) {

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
	private Position[] startPositions() {

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

	// PRINT POSITIONS | Print all start positions.
	// 
	public void printPositions() {
		Position[] pos = startPositions();
		for (Position p : pos) {
			System.out.println(p);
		}
	}

	// IS WINNING POSITION | Determine if Position p is winning position, i.e
	// 										 | there exists a sequence of valid moves such that
	// 										 | the first player wins the game. If the total moves
	// 										 | is an odd number, then it is a winning position.
	//
	private boolean isWinningPosition(Position p) {
		// calculate distances between substrings & strings
		Integer[] distsA = distancesBetween(p.a, A);
		Integer[] distsB = distancesBetween(p.b, B);
		// if one dists contains an odd num & the other an even, then winning pos
		boolean x = hasOdd(distsA) && hasEven(distsB);
		boolean y = hasEven(distsA) && hasOdd(distsB);
		return x || y;
	}

	// HAS ODD | Return true if arr contains odd number.
	//
	private boolean hasOdd(Integer[] arr) {
		for (Integer x : arr)
			if (x % 2 == 1)
				return true;
		return false;
	}

	// HAS EVEN | Return true if arr contains even number.
	//
	private boolean hasEven(Integer[] arr) {
		for (Integer x : arr)
			if (x % 2 == 0)
				return true;
		return false;
	}

	// DISTANCES BETWEEN | Return the numbers of chars needed to be appended
	// 					 				 | to x, so that y ends with x.
	//
	private Integer[] distancesBetween(String x, String y) {

		Set<Integer> dists = new HashSet<Integer>();
		// empty string can be any distance from end
		if (x.length() == 0)
			// add all distances
			for (int i = 1; i <= y.length(); i++)
				dists.add(i);
		else {
			int k = 0;
			// while occurences from index k
			while (y.indexOf(x, k) != -1) {
				// end index of substring in y
				int i = y.indexOf(x, k) + x.length() - 1;
				// num of remaining chars to end of y
				dists.add(y.length() - 1 - i);
				k = i+1;
			}
		}
		// convert to list
		Integer[] arr = dists.toArray(new Integer[dists.size()]);
		return arr;
	}

	// POSITION | The Position class represents a game position (a,b), where
	// 					| a & b are valid substrings of the main Strings A & B
	// 					| respectively.
	//
	class Position {

		public String a;
		public String b;

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
