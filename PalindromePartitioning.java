package leetCode;

import java.util.ArrayList;
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

public class PalindromePartitioning {

	public static void main(String[] args){
		
		//1st, input a string, with punctuation and spaces;
		System.out.println("This is a Valid Palindrome program.");
		System.out.println("Please input the sentence:");
		
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		input.close();

		Stopwatch Watch = new Stopwatch();
		ArrayList<ArrayList<String>> palindromeList = new ArrayList<ArrayList<String>>();
		
		palindromeList = palindromePartions(inputStr);
		
		printArrayListwithArrayList(palindromeList);
		
		System.out.println("The time cost: " + Watch.elapsedTime() +". ");
		
	} // end main();

	private static void printArrayListwithArrayList(
			ArrayList<ArrayList<String>> palindromeList) {
		// TODO Auto-generated method stub
		
		for(ArrayList<String> ar:palindromeList){
			printArrayList(ar);
		}
		
	} // end printArrayListwithArrayList() method;

	private static void printArrayList(ArrayList<String> arrayList) {
		// TODO Printout every string in the arrayList;
		System.out.print(" size: " + arrayList.size());
		
		for(String s:arrayList){
			System.out.print(" " + s);
		}
		
		System.out.println();
		
	} // end printArrayList() method;

	
	
	private static ArrayList<ArrayList<String>> palindromePartions(String inputStr) {
		// TODO Get all sub-string from inputStr which are palindrome string
		
		ArrayList<ArrayList<String>> retList = new ArrayList<ArrayList<String>>();
		ArrayList<String> subStrList = new ArrayList<String>();
		
		int Len = inputStr.length();
		if(Len == 0){
			return retList;
		}
		
		buildArrayofArraylist(inputStr, subStrList, retList);
		
		return retList; 
		// for PalindromePartitionII, return the minimum size-1 of the string ArrayList;
		
	} // end palindromePartions() method;

	private static void buildArrayofArraylist(String inputStr, ArrayList<String> subStrList, ArrayList<ArrayList<String>> retList) {
		// TODO Auto-generated method stub
		int Len = inputStr.length();
		if(Len == 0){
			
			retList.add(subStrList);
			return;
		}
		
		for(int i=1; i<=Len; i++){ // i is the length of the subString;
			
			String subStr = inputStr.substring(0, i);
				
				ArrayList<String> subStrList2 = new ArrayList<String>(subStrList);
				if(checkPalindrome(subStr)){
									
					subStrList2.add(subStr);
					buildArrayofArraylist(inputStr.substring(i), subStrList2, retList);
				
				}
				
		} // end for i<=Len loop;
		
	} // end buildArrayofArraylist() method;

	private static boolean checkPalindrome(String inputStr) {
		// TODO To check if a string is Palindrome
		int Len = inputStr.length();
		
		int leftP = 0; 
		int rightP = Len-1;
		//char left = 'a';
		//char right = 'a';
		
		while(leftP < rightP){
			
			while(!checkValid(inputStr.charAt(leftP))){
				++leftP;
			} //if the charAt(leftP) is a punctuation, check next right;
			
			while(!checkValid(inputStr.charAt(rightP))){
				--rightP;
			} //if the charAt(rightP) is a punctuation, check next left;
			
			char left = inputStr.charAt(leftP);
			char right = inputStr.charAt(rightP);
			
			if(left!=right && left != right +'a' - 'A' && right != left +'a' -'A'){
				
				return false;				
			} // end if false condition; a=A, a=a and A=a are true conditions; 
			
			leftP++;
			rightP--;
			
		} // end while leftP<rightP loop;
				
		return true;
		
	} // end checkPalindrome() method;

	private static boolean checkValid(char charA) {
		// TODO Check if a character is a valid letter, only A-Z, a-z or numbers are valid;
		if(charA>='a' && charA<='z')
			return true;
		if(charA>='A' && charA<='Z')
			return true;
		if(charA>='0' && charA<='9')
			return true;
		
		return false;
		
	} // end checkValid() method;
	
} // end of everything in PalindromePartitioningNaive class;
