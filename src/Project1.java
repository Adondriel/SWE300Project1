import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author merlin
 * @studentEditors Joshua McMillen, Adam Pine, Benjamin Uleau * Problem 11.6.2
 *                 from Programming Challenges. Using memoization
 * 
 *                 After taking user input, this program will recursively check
 *                 for the number of ways in which a pattern can be found as a
 *                 subsequence within a target text Memoization will be used to
 *                 cache results of each recursive call into a two dimensional
 *                 array - this will limit the depth of recursion, returning the
 *                 value within a cell when it is not the initialized value of
 *                 -1 as opposed to continuing the recursion to the same result.
 *                 This will leave the solution count in the final cell of the
 *                 array.
 */
public class Project1 {

	/**
	 * @param args
	 *            Get User Input for Number of Runs to attempt For each Run
	 *            Get user input for the target text
	 *            Get user input for the pattern to search the target text for 
	 *            find and print the # of distinct sequences in the target text that the pattern
	 *            matches
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		int totalRuns = Integer.parseInt(userInput.readLine());

		for (int currRun = 0; currRun < totalRuns; currRun++) {
			String targetText = new String(userInput.readLine());
			String pattern = new String(userInput.readLine());
			System.out.println(findDistinctSubsequences(pattern, targetText));
		}
	}

	/**
	 * @param pattern
	 *            : Find the length of the pattern
	 * @param targetText
	 *            : Find the length of the targetText Initialize the
	 *            matchesFound array Begin recursive memoization to find
	 *            Subsequences
	 * @return distinct subsequences
	 */
	private static int findDistinctSubsequences(String pattern, String targetText) {

		int patternLength = pattern.length();
		int targetTextLength = targetText.length();
		int matchesFound[][] = initializeArray(patternLength, targetTextLength);

		return storeCount(matchesFound, pattern, (patternLength - 1), targetText, (targetTextLength - 1));
	}

	/**
	 * @param totalRows
	 *            : equal to length of pattern ( n )
	 * @param totalCols
	 *            : equal to length of target text ( m ) Create an ( n ) x ( m )
	 *            array and initialize each cell to -1
	 * @return the created 2d Array
	 */
	private static int[][] initializeArray(int totalRows, int totalCols) {

		int tempArray[][] = new int[totalRows][totalCols];

		for (int row = 0; row < totalRows; row++) {
			for (int col = 0; col < totalCols; col++) {
				tempArray[row][col] = -1;
			}
		}
		return tempArray;
	}

	/**
	 * @param pattern
	 *            : user inputed text
	 * @param currPatternPos
	 *            : row position
	 * @param targetText
	 *            : user inputed text
	 * @param currTargetPos
	 *            : column position Store the count within a cell located at
	 *            given row x column position if the cell does not already
	 *            contain a count greater than -1
	 * @return count within given row and column or 1 if current position in
	 *         pattern is -1
	 */
	private static int storeCount(int[][] matchesFound, String pattern, int currPatternPos, String targetText, int currTargetPos) {

		if (currPatternPos == -1) {
			return 1;
		}

		if (matchesFound[currPatternPos][currTargetPos] == -1) {
			matchesFound[currPatternPos][currTargetPos] = findCount(matchesFound, pattern, currPatternPos, targetText,
					currTargetPos);
		}

		return matchesFound[currPatternPos][currTargetPos];

	}

	/**
	 * @param pattern
	 * @param currPatternPos
	 * @param targetText
	 * @param currTargetPos
	 *            Check for the following: 
	 *            -- If the pattern has been exhausted
	 *            -- If the current position in the pattern is larger than the current position in the target 
	 *            -- If the both positions are equal 
	 *            ---- If yes, then check if all characters leading up to position match 
	 *            -- If the characters at both positions match
	 *            ---- If yes, then do the following: 
	 *            ------ Check one position left of both the current pattern position and current target position 
	 *            ------ Check one position left of only the current target position, maintaining the current pattern position 
	 *            -- If all Checks fail, Check one position left of only the current target position, maintaining the current pattern
	 *            position
	 * @return count of matches
	 */
	private static int findCount(int[][] matchesFound, String pattern, int currPatternPos, String targetText,
			int currTargetPos) {
		if (currPatternPos > currTargetPos) {
			return 0;
		}

		if (currPatternPos == currTargetPos) {
			return checkForMatch(pattern, currPatternPos, targetText);
		}

		if (pattern.charAt(currPatternPos) == targetText.charAt(currTargetPos)) {
			return (storeCount(matchesFound, pattern, (currPatternPos - 1), targetText, (currTargetPos - 1))
					+ storeCount(matchesFound, pattern, currPatternPos, targetText, (currTargetPos - 1)));
		}

		return (storeCount(matchesFound, pattern, currPatternPos, targetText, (currTargetPos - 1)));

	}

	/**
     * @param pattern
     * @param maxCharPos
     * @param targetText
     * Check all proceeding characters up until the current pattern position
     * @return 0 if not an exact match and 1 if all characters do match
     */
    private static int checkForMatch( String pattern , int maxCharPos , String targetText ){
    	
        for ( int charPos = 0; charPos <= maxCharPos; charPos++ ){        	
        	if ( pattern.charAt( charPos ) != targetText.charAt( charPos ) ){        		
        		return 0;        		
        	}        	
        }
        
        return 1;
        
    }
}
