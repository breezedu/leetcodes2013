package leetCode;

import java.util.Scanner;
import java.util.Stack;

public class ReverseWordsinaString {

	public static void main(String[] args){
		
		System.out.println("This is Reverse Words in a String program.");
		
		//1st, ask user to input a sentence
		System.out.println("Please input a sentence.");
		Scanner input = new Scanner(System.in);
		System.out.print("s:");
		String s = input.nextLine();
		input.close();
		
		
		//2nd, reverse the sentence:
		String revers = reverseWords(s);
		
		//printout the reversed sentence:
		System.out.println("After revrse:" + revers +". ");
		
	}//end main();

	/**********
	 * use string.split(" ") to break the sentence into an array of string[];
	 * push all none " " none "" string[i] into a stack;
	 * pop() the stack and add the popped words to a new sentence:
	 * @param s
	 * @return
	 */
	private static String reverseWords(String s) {
		// TODO Auto-generated method stub
		if(s.length() == 0) return s;
		
		String[] split = s.split(" ");
		printArray(split);
		
		int Len = split.length;
		if(Len == 0) return "";
		
		Stack<String> stc = new Stack<String>();
		for(int i=0; i<Len; i++){
			stc.push(split[i]);
		}
		
		String retStr = stc.pop();
		System.out.println("str:" + retStr);
		
		while(!stc.isEmpty()){
			if(!stc.peek().equals("")){
			
				retStr += " " +stc.pop();
			} else {
				stc.pop();
			}
		}
		
		return retStr;
	}

	private static void printArray(String[] split) {
		// TODO Auto-generated method stub
		int Len = split.length;
		for(int i=0; i<Len; i++){
			System.out.print(" (" +split[i] +")");
		}
		System.out.println();
	}

}//end of everything in ReverseWordsinaString class
