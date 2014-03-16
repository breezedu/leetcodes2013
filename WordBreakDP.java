package leetCode2013;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/********
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space-separated 
 * sequence of one or more dictionary words. 
 * If any (every) piece of the sub-word from s could be found in dict
 * return true :)
 * 
 * For example, given 
 * s = "leetcode", 
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author Frog
 */
public class WordBreakDP {
	
	public static void main(String[] args){
		
		System.out.println("This is a word break check program.");
		
		
		//1st, ask user to input string s
		System.out.println("Please into the string s.");
		Scanner input = new Scanner(System.in);
		System.out.print(" s: ");
		String s = input.next();
		input.close();
		
		//String s = "leetcode";
		
		//2nd, add an array of strings into a HashSet:
		Set<String> dict = new HashSet<String>();
		String[] dictStr = {"leet", "code"};
		for(int i=0; i<dictStr.length; i++){
			dict.add(dictStr[i]);
		}
		
		
		//3rd, call checkBreak method to check if the input string could be segmented according to the dict set;
		boolean checkBreak = wordBreak(s, dict);
		
		//printout the result :)
		if(checkBreak){
			System.out.println("Yes, the string could be sigmented into a sentence.");
		} else {
			System.out.println("No, the string could not be broken any more.");
		}
		
	} // end of main();

	/*************
	 * Use Dynamic Programming method:
	 * if s.substring(0, m) could be break according to the dictionary set, and 
	 * s.subtring(m, n) could also be found in the dictionary set, that means
	 * s.substring(0, n) is breakable too!
	 * 
	 * so, we create an array of toBreak[] booleans, toBreak[0]=true. the traversal
	 * the string s, to check if at any index the s.substring(0, index) is breakable or not;
	 * at the end, return if toBreak[s.length] is breakable or not.
	 * 
	 * @param s
	 * @param dict
	 * @return
	 */
	private static boolean wordBreak(String s, Set<String> dict) {
		// TODO Check if the string s could be broken into sub-words according to the dictionary.
		int Len = s.length();
		
		//create an array of booleans toBreak[]. toBreak[i]=true
		//if s.substring(0,i) could be broke according to the dictionary
		boolean[] toBreak = new boolean[Len+1];
		toBreak[0] = true;
		
		for(int i=1; i<=Len; i++){
			
			for(int j=i-1; j>=0; j--){
				
				if(toBreak[j] && dict.contains( s.substring(j, i)) ){
					toBreak[i] = true;
					break; 				//break the inner loop;
				}
				
			}//end for j>=0 loop;
		}//end for i<=Len loop;
		
		//if the last toBreak[] element is true, the whole string could be segmented :)
		return toBreak[Len];
	} // end wordBreak() method;

} // end of everything in WordBreak class;
