package leetCode;

import java.util.Scanner;

/******************
 * Given a string s, 
 * partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * 
 * return:
 * ["aa","b"],
 * ["a","a","b"]
 * 
 * @author Frog
 * PS. This program work fine with "AAbBaa"
 * but, to check a string with punctuation: "aAa?b..<aa"
 * we have to build a new string without any punctuation first.
 * 
 */
 
public class PalindromePartitioningIIDPmatrix {

	public static void main(String[] args){
		
		//1st, input a string, with punctuation and spaces;
		System.out.println("This is a Valid Palindrome II DynamicProgram with matrix.");
		System.out.println("Please input the sentence:");
		
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		input.close();
		
		Stopwatch Watch = new Stopwatch();
	//	ArrayList<ArrayList<String>> palindromeList = new ArrayList<ArrayList<String>>();
		
		int minCut = palindromePartions(inputStr);
		
	//	printArrayListwithArrayList(palindromeList);
		System.out.println("The minimum cut should be " + minCut +". ");
		
		System.out.println("The time cost: " + Watch.elapsedTime() +". ");
		
	} // end main();

	private static int palindromePartions(String s) {
		// TODO Get all sub-string from inputStr which are palindrome string
		// Use dp to get all the palindrome 
		int Len = s.length();
        
		boolean[][] validPalin = new boolean[Len][Len]; // validPalin[i][j]=true means i-j is a valid Palindrome string;
		int[] dp = new int[Len+1]; // dp[i] is the minCut needed from index i to the end;
		
		for(int i=Len; i>=0; i--){
			dp[i] = Len -i -1; // this is the worst condition, when the string could only be divided by single letter;
			
		} // end for i>=0 loop; end assigning the worst condition;
		
		for(int i=Len-2; i>=0; i--){
			
			for(int j=i; j<Len; j++){
				
				if((s.charAt(i)==s.charAt(j)) &&(j<i+2||validPalin[i+1][j-1])){
					
					validPalin[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j+1] + 1);
				}
				
			} // end inner for j<Len loop;
			
		} // end outer for i>=0 loop;
		
		for(int i=0; i<Len; i++){
			System.out.print(" " +dp[i]);
		}
		System.out.println();
		
		printMatrix(validPalin);
		
		return dp[0];
       
			
	} // end palindromePartions() method;

	private static void printMatrix(boolean[][] validPalin) {
		// TODO Printout a matrix;
		int Row = validPalin.length;
		int Col = validPalin[0].length;
		
		for(int i=0; i<Row; i++){
			
			for(int j=0; j<Col; j++){
				
				System.out.print("  " +validPalin[i][j]);
			}
			
			System.out.println();
		} // end for i<Row loop
		
	}// end of printMatrix() method;
	
	
} // end of everything in PalindromePartitioningNaive class;
