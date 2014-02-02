package leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/********
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space-separated 
 * sequence of one or more dictionary words.
 * 
 * For example, given 
 * s = "catsanddog", 
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * Return ["cat sand dog", "cats and dog"].
 * 
 * @author Frog
 */
public class WordBreakIIArrayList {
	
	public static void main(String[] args){
		
		System.out.println("This is a word break check program.");
		
		System.out.println("Please input the word to break: ");
	//	String[] dictStr = {"cat", "cats", "and", "sand", "dog"};
		String[] dictStr = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		
		Scanner input = new Scanner(System.in);
		String s = input.next();
		input.close();
		
		Set<String> dict = new HashSet<String>();
		
		int strLen = dictStr.length;
		
		for(int i=0; i<strLen; i++){
			
			dict.add(dictStr[i]);
		} // end for i<strLen loop;
		
		String sentence = "";
		
		ArrayList<String> sentArrayList = new ArrayList<String>();
		
		sentArrayList = wordBreak(s, dict, sentence, sentArrayList);
		
		printArrayList(sentArrayList);
		
	} // end of main();

	private static void printArrayList(ArrayList<String> sentArrayList) {
		// TODO Auto-generated method stub
		System.out.println("Printing AL: ");
		
		for(String str:sentArrayList){
			System.out.println(str);
		}
		
	} // end printArrayList() method.

	private static ArrayList<String> wordBreak(String s, Set<String> dict, String sentence, ArrayList<String> sentArrayList) {
		// TODO Check if the string s could be broken into sub-words according to the dictionary.
		/*****
		 * use a recursion method to check if the word s could be broken into a sentence
		 * according to the dictionary;
		 */
		if(dict.contains(s)){
			
			sentence += " " +s;
			//System.out.println(" " + sentence +".    Got one sentence.");
			//System.out.println();
			sentArrayList.add(sentence);
			return sentArrayList;
			
		} // end return condition;
		
		int Len = s.length();
		for(int i=0; i<Len; i++){
		
			String partialStr = s.substring(0, i);
			
			if(dict.contains(partialStr)){
				
				/********
				 * Mark: every time calling this recursion again;
				 * there might be different substrings; like the cat+sand and cats+and
				 * to avoid the duplication, we have to declare a new string, then
				 * pass the new string to the recursion;
				 */
				String newSen = sentence +" " + partialStr; // this new string is very important!
				
				//System.out.print(" " + s.substring(0,i));
				wordBreak( s.substring(i), dict, newSen, sentArrayList);
					
			}  // end if dict.contains(partial string);
				
		} // end for key:strSet loop;
		
		return sentArrayList;
				
	} // end wordBreak method;
	
} // end of everything in WordBreak class;
