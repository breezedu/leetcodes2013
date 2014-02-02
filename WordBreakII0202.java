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
 * This program even works fine with input string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
 * 
 * @author Frog
 */
public class WordBreakII0202 {
	
	public static void main(String[] args){
		
		System.out.println("This is a word break II program.");
		
		System.out.println("Please input the word to break: ");
		String[] dictStr = {"cat", "cats", "and", "sand", "dog", "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		
		Scanner input = new Scanner(System.in);
		String s = input.next();
		input.close();
		
		Set<String> dict = new HashSet<String>();
		
		int strLen = dictStr.length;
		
		for(int i=0; i<strLen; i++){
			
			dict.add(dictStr[i]);
		} // end for i<strLen loop;
		
		ArrayList<String> retSentences = wordBreak(s, dict);
		
		printArrayList(retSentences);
		
	} // end of main();

	private static void printArrayList(ArrayList<String> Sentence) {
		// TODO Auto-generated method stub
		System.out.println();
		for(String c:Sentence){
			System.out.println(c);
		}
		
		System.out.println("The size of the arrayList is: " +Sentence.size() +". ");
	} // end printArrayList method;

	private static ArrayList<String> wordBreak(String s, Set<String> dict) {
		// TODO Check if the string s could be broken into sub-words according to the dictionary.
		/*****
		 * use a recursion method to check if the word s could be broken into a sentence
		 * according to the dictionary;
		 */
		
		ArrayList<String> Sentence = new ArrayList<String>();
		// first check if the string s is breakable or not

		
		String senStr = "";
		int Max = 0;
		for(String key:dict){
			if(key.length()>Max){
				Max = key.length();
			}
		} // end for key:dict loop; found the max length of the word in the dictionary;
		
		if(checkBreakable(s, dict, Max)==false){
			return Sentence;
		}
		
	//	System.out.println("L81: s = "+s);
		Sentence = combineSentence(s, dict, Sentence, senStr, Max);
		
		return Sentence;
	} // end wordBreak method;

	private static boolean checkBreakable(String s, Set<String> dict, int max) {
		// TODO to check if a word is breakable according to the dictionary;
		/******
		 * to a string of length 0 to Len;
		 * at any index i, if there's no sub-string around i 
		 * with the length <=max could be found in the dict
		 * then the original word string could not be broken according to the dictionary;
		 */
		
		for(int i=0; i<max; i++){
			s = " " +s+" ";
		} // end for i<max loop; add #max of " " to the front and after the end of the string;

		// System.out.println("max = " + max +". s=" + s +". ");
		int Len = s.length();
		boolean[] retBoolean = new boolean[Len];
		
		for(int i=max; i<Len-max; i++){
			//System.out.print("i=" +i+". ");
			
			for(int j=1; j<=max; j++){
				
				for(int m = i-j+1; m<=i; m++){
					
					String temStr = s.substring(m, m+j);
					if(dict.contains(temStr)){
						retBoolean[i] = true;
				//		System.out.print(" i=" +i+ " " + temStr +". ");
						j=max; // this will jump out of j<=max loop;
					}
				} // end for m<=i loop; m is the start point of substrings;
				
			} // end for j<max loop; this is the length of substrings at i;
			
			//System.out.println();
		} // end for i<Len loop, this is the central index of substrings at i;
		
		for(int i=max; i<Len-max; i++){
			
			if(retBoolean[i] == false){
				return false;
			}
		}	
		
		return true;
	} // end checkBreakable() method;

	private static ArrayList<String> combineSentence(String s, Set<String> dict, ArrayList<String> sentence, String senStr, int max) {
		// TODO Auto-generated method stub
		
			int Len = s.length();
			int subLen=0;
			
			if(Len > max){
				subLen = max;
			} else {
				subLen = Len;
			} // subLen is the boundary of the i loop;
			// if the length of s is long enough, we just need to check all s.substrings within the max length of words in the dict
			// else, we just need to check till the end of the s.length;
			
			for(int i=0; i<=subLen; i++){
		
				String partialStr = s.substring(0, i);
			
				if(dict.contains(partialStr) && i!=Len){

					//senStr += partialStr;
					//senStr += " ";
					String newStr = senStr + " " + partialStr;
				//	System.out.print("L108: " + senStr +". ");
					sentence = combineSentence( s.substring(i), dict, sentence, newStr, max);
					
				} else if(dict.contains(partialStr) && i==Len){
					
					String newStr = senStr+" " + partialStr;
					sentence.add(newStr.substring(1)); // remove the first " " in the sentence;
					return sentence;
				} // end if-else loop;
				// when i did not reach the end of the s string; we have to recursion;
				// if i reached the end of the string, we should add the whole sentence to the arrayList, then return it.
				
			} // end for i<subLen loop;
		
			//System.out.println("L93: " +senStr);
			return sentence;
			
	} // end combineSentence() method;
	
} // end of everything in WordBreak class;
