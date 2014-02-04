package leetCode;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/*****************
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 * 
 * @author Frog
 *
 */
public class SingleNumberI {
	
	public static void main(String[] args){
		
		/******
		 * build a array with double elements except for one;
		 */
		
		System.out.println("This is a single number problem.");
		System.out.println("Please input how many elements are there in the array: (odd)");
		
		Scanner input = new Scanner(System.in);
		
		int Num = input.nextInt();
		input.close();
		
		int[] numbers = creatNumArray(Num);
		
		printArray(numbers);
		
		int single = pickSingleNumber(numbers);
		System.out.println("The single number in the array is: " + single);
		
		int single2 = xorSingleNumber(numbers);
		System.out.println("Pick the single number with XOR method." + single2);
		
	} // end of main();

	/********
	 * XOR method could return the single number in the array within O(n) time and O(1) space;
	 * but XOR could not return the index of the single number.
	 * @param numbers
	 * @return
	 */
	private static int xorSingleNumber(int[] numbers) {
		// TODO Auto-generated method stub
		int ret = 0;
		for(Integer i:numbers){
			ret = ret^i;
		}
		return ret;
	} // end xorSingleNumber() method;

	private static void printArray(int[] num) {
		// TODO Printout all elements in the array;
		int Len = num.length;
		for(int i=0; i<Len; i++){
			System.out.print(" " + num[i]);
		}
		System.out.println();
		
	} // end printArray() method;

	private static int[] creatNumArray(int num) {
		// TODO Auto-generated method stub
		int[] numArray = new int[num];
		
		for(int i=0; i<num/2; i++){
			
			numArray[i] = (int)(Math.random()*100);
			numArray[num-i-1] = numArray[i];
		}
		numArray[num/2] = (int)(Math.random()*100);
		
		return numArray;
		
	} // end of creatNumArray() method;

	/*********
	 * with a hashTable, the running time is also O(n);
	 * but the space is O(hash);
	 * @param numbers
	 * @return
	 */
	private static int pickSingleNumber(int[] numbers) {
		// TODO Auto-generated method stub
		int Len = numbers.length;
		if(Len ==0){
			return 0;
		}
		
		Hashtable<Integer, Integer> numHash = new Hashtable<Integer, Integer>();
		
		// check if numbers[i] in the hashTable, if not: put the numbers[i] and it's index into the table
		// if yes: remove numbers[i] from the table;
		for(int i=0; i<Len; i++){
			if(!numHash.containsKey(numbers[i])){
				
				numHash.put(numbers[i], i);
				
			} else {
				
				numHash.remove(numbers[i]);
			}
		} // end for i<Len loop; all numbers[i] have been check through the hashTable;
		
		Set<Integer> keys = numHash.keySet();
		
		int ret=0;
		for(Integer key: keys){
			System.out.println("  " + key +" "+ numHash.get(key));
			ret = key;
		} // end for key: Keys loop; print out the final single number left in the hashTable;

		return ret;
	} // end pickSingleNumber() method;
	
	

} // end of everything in SingleNumber class;
