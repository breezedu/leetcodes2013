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
		
		/******
		 // merge all these methods into checkPalidrome() method;
		System.out.println("The string inputed is: " + inputStr);
		String cleanStr = removePunctuation(inputStr);
		
		System.out.println("Removed the punctuations: " + cleanStr);
		
		//change a to A
		String upCase = upCaseStr(cleanStr);
		System.out.println("After upCase change: " + upCase);
		*/
		boolean palindrome = checkPalindrome(inputStr);
		
		if(palindrome){
			System.out.println("The input string is a Palindrome.");
		} else {
			System.out.println("The input string is not a Palindrome.");
		}
		
		
	} // end main();

	private static boolean checkPalindrome(String inputStr) {
		// TODO To check if a string is palindrome
		int Len = inputStr.length();
		
		int leftP = 0; 
		int rightP = Len-1;
		char left = 'a';
		char right = 'a';
		
		while(leftP < rightP){
			
			while(!checkValid(inputStr.charAt(leftP))){
				++leftP;
			}
			while(!checkValid(inputStr.charAt(rightP))){
				--rightP;
			}
			
			left = inputStr.charAt(leftP);
			right = inputStr.charAt(rightP);
			
			if(left!=right && left != right +'a' - 'A' && right != left +'a' -'A'){
				
				return false;				
			} // end if false condition;
			
			leftP++;
			rightP--;
			
		} // end while leftP<rightP loop;
				
		return true;
		
	} // end checkPalindrome() method;

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
