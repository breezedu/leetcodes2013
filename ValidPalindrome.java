package leetCode;

import java.util.Scanner;

/******************
 * Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * 
 * @author Frog
 *
 */

public class ValidPalindrome {

	public static void main(String[] args){
		
		//1st, input a string, with punctuation and spaces;
		System.out.println("This is a Valid Palindrome program.");
		System.out.println("Please input the sentence:");
		
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		input.close();
		
		System.out.println("The string inputed is: " + inputStr);
		String cleanStr = removePunctuation(inputStr);
		
		System.out.println("Removed the punctuations: " + cleanStr);
		
		//change a to A
		String upCase = upCaseStr(cleanStr);
		System.out.println("After upCase change: " + upCase);
		
		boolean palindrome = checkPalindrome(upCase);
		
		if(palindrome){
			System.out.println("The input string is a Palindrome.");
		} else {
			System.out.println("The input string is not a Palindrome.");
		}
		
		
	} // end main();

	private static boolean checkPalindrome(String upCase) {
		// TODO To check if a string is palindrome
		int Len = upCase.length();
		for(int i=0; i<Len/2; i++){
			if(upCase.charAt(i) != upCase.charAt(Len-i-1))
				return false;
		}
		
		return true;
	} // end checkPalindrome() method;

	private static String upCaseStr(String cleanStr) {
		// TODO Change all lowercase letters to uppcase;
		int Len = cleanStr.length();
		String retStr = "";
		for(int i=0; i<Len; i++){
			if(cleanStr.charAt(i) >= 'A' && cleanStr.charAt(i)<='Z'){
				 char temp = (char) (cleanStr.charAt(i) + 'a'-'A');  
				 retStr += temp;
				 
			} else {
				char temp = cleanStr.charAt(i);
				retStr += temp;
			}
		}
		
		return retStr;
		
	} // end upCaseStr() method;

	private static String removePunctuation(String inputStr) {
		// TODO Auto-generated method stub
		String retStr = "";
		String rightToLeft = "";
		
		int Len = inputStr.length();
		
		for(int i=0; i<Len; i++){
			if(checkValid( inputStr.charAt(i) )){
				retStr += inputStr.charAt(i);
				rightToLeft = inputStr.charAt(i) + rightToLeft;
			}
		}
		
		System.out.println("left to right: " + retStr);
		System.out.println("right to left: " + rightToLeft);
		
		return retStr;
	} // end removePunctuation() method;

	private static boolean checkValid(char charA) {
		// TODO Auto-generated method stub
		if(charA>='a' && charA<='z')
			return true;
		if(charA>='A' && charA<='Z')
			return true;
		if(charA>='0' && charA<='9')
			return true;
		
		return false;
		
	} // end checkValid() method;
	
} // end of everything in ValidPalindrome class;
