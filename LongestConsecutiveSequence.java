package leetCode;

import java.util.HashSet;
import java.util.Scanner;

/*********************
 * Given an unsorted array of integers, 
 * find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 * 
 * @author Frog
 *
 */
public class LongestConsecutiveSequence {
	
	public static void main(String[] args){
		// first create an array with N elements; initialize a random value to each element;
		
		System.out.println("This is a Longest Consecutive Sequence program.");
		System.out.println("Please input how many elements in the array?");
		
		Scanner input = new Scanner(System.in);
		
		int Num = input.nextInt();
		input.close();
		
		int[] seqArray = new int[Num];
		for(int i=0; i<Num; i++){
			
			seqArray[i] = (int) (Math.random()*10);
		} // end for i<Num loop; the array has been built;
		
		printArray(seqArray);
		
		
		int conNum = longestConsecutive(seqArray);
		
		System.out.println("There are " + conNum +" elements in the longest consecutive sequence. ");
		
		
		
	} // end of main();

	private static void printArray(int[] seqArray) {
		// TODO Printout all elements in a array;
		int Len = seqArray.length;
		for(int i=0; i<Len; i++){
			
			System.out.print(" " + seqArray[i]);
		}
		System.out.println();
		
	} // end printArray() method;

	private static int longestConsecutive(int[] seqArray) {
		// TODO Calculate the longest consecutive sequence;
		int LC = 1; // this is the worst condition;
		int Len = seqArray.length;
		
		HashSet<Integer> numSet = new HashSet<Integer>();
		for(int i=0; i<Len; i++){
			numSet.add(seqArray[i]);
			
		} // end for i<Len loop; all array elements have been put to the hashSet;
		
		for(int i=0; i<Len; i++){
			int temCon = 0;
			int val = seqArray[i];
			temCon = checkConsecutive(temCon, val, numSet);
			
			if(temCon > LC){
				LC = temCon;
			}
		} // end for i<Len loop; all consecutive numbers for each elements has been checked;
		
		return LC;
	}

	private static int checkConsecutive(int temCon, int num, HashSet<Integer> numSet) {
		// TODO Auto-generated method stub
		if(numSet.contains(num)){
			temCon++;
			numSet.remove(num);
			
			temCon = checkConsecutive(temCon, num+1, numSet);
			temCon = checkConsecutive(temCon, num-1, numSet);
		} 
		
		return temCon;
				
	} // end checkConsecutive() method;

}// end of everything in LongestConsecutiveSequence class
