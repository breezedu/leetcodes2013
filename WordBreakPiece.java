package leetCode;

import java.util.HashSet;
import java.util.Set;

/********
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space-separated 
 * sequence of one or more dictionary words. 
 * If any piece of the sub-word from s could be found in dict
 * return true :)
 * 
 * For example, given 
 * s = "leetcode", 
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author Frog
 */
public class WordBreakPiece {
	
	public static void main(String[] args){
		
		System.out.println("This is a word break check program.");
		
		String s = "a";
		Set<String> dict = new HashSet<String>();
		dict.add("a");
		//dict.add("code");
		
		boolean checkBreak = wordBreak(s, dict);
		
		if(checkBreak){
			System.out.println("Yes, the string could be broken into one or more word sequences.");
		} else {
			System.out.println("No, the string could not be broken any more.");
		}
		
	} // end of main();

	private static boolean wordBreak(String s, Set<String> dict) {
		// TODO Check if the string s could be broken into sub-words according to the dictionary.
		/*****
		 * get all sub-string from original string s;
		 * put them into a hashSet;
		 * compare the sub-string hashSet with the dict set;
		 * if we get any match, the word could be break;
		 */
		boolean ret = false;
		
		HashSet<String> strSet = getSubStr(s);
		System.out.println("\nChecking all keys in the strSet hashSet: ");
		for(String key:strSet){
		//	System.out.print(" " + key);
			
			if(dict.contains(key)){
				System.out.println("Got one: " + key +". ");
				ret = true;
				
			} // end if dict contains(key) condition;
			
		} // end for key:strSet loop;
		
		System.out.println();
		return ret;
		
	} // end wordBreak() method;

	
	private static HashSet<String> getSubStr(String s) {
		// TODO Auto-generated method stub
		HashSet<String> retSet = new HashSet<String>();
		int Len = s.length();
		
		for(int i=1; i<=Len; i++){ // this is the length of the subString;
			
			for(int j=0; j<=Len-i; j++){
				
				String temStr = s.substring(j, j+i);
				retSet.add(temStr);
			//	System.out.print(" " + temStr +". ");
				
			} // end inner for j loop;
			
			//System.out.println();
		} // end outer for i<Len-1 loop;
		
		return retSet;
		
	} // end getsubStr() method;

} // end of everything in WordBreak class;
