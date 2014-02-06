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

		boolean palindrome = checkPalindrome(inputStr);
		
		if(palindrome){
			System.out.println("The input string is a Palindrome.");
			
		} else {
			System.out.println("The input string is not a Palindrome.");
			
		} // output whether the inputString is a palindrome or not.
		
		
	} // end main();

	private static boolean checkPalindrome(String inputStr) {
		// TODO To check if a string is Palindrome
		int Len = inputStr.length();
		
		int leftP = 0; 
		int rightP = Len-1;
		//char left = 'a';
		//char right = 'a';
		
		while(leftP < rightP){
			
			while(!checkValid(inputStr.charAt(leftP))){
				leftP++;
			} //if the charAt(leftP) is a punctuation, check next right;
			
			while(!checkValid(inputStr.charAt(rightP))){
				rightP--;
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
	
} // end of everything in ValidPalindrome class;
