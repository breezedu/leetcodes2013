package leetCode2014;

import java.util.Scanner;

/*********************************************
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * @author Jeff
 *
 */
public class FindMinimuminRotatedSortedArray {

	public static void main(String[] args){
		
		System.out.println("This is Find Minimum in Rotated Sorted Array class.");
		
		//1st, create an rotated sorted array;
		int[] num = creatSortedRotatedArray();
		
		
		//2nd, find the Minimum in the rotated sorted array:
		
		int min = findMini(num);
		
		System.out.println("The minimum num is: " + min);
		
		
	}

	private static int findMini(int[] num) {
		// TODO Auto-generated method stub
		
		int len = num.length;
		if(num[0] < num[len-1]) return num[0];
		
		return binarySearch(num, 0, len-1);
		
	}
	

	private static int binarySearch(int[] num, int start, int end) {
		// TODO Auto-generated method stub
		if(start == end || num[start] < num[end])
			return num[start];
		
		int median = (start + end)/2;
		
		if(median == 0){
			
			if(num[median] > num[end]) return num[end];
			else return num[median];
		}
		
		if( num[median] < num[median-1]) {
			return num[median];
			
		} else if(num[median] >= num[start] ){
			
			return binarySearch(num, median+1, end);
			
		} else if(num[median] < num[start]){
			
			return binarySearch(num, start, median-1);
			
		}
		
		
		return 0;
		
	}

	
	
	
	private static int[] creatSortedRotatedArray() {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please input the length of the array: [10, 20, 50 or 500] \n len = ");
		int len = input.nextInt();
		
		//create a sorted array:
		int[] num = new int[len];
		
		num[0] = (int) (Math.random() * 10) +1;
		
		for(int i=1; i<len; i++){
			
			num[i] = num[i-1] + (int)(Math.random()*5) + 1;
		}
		
		
		//ask user to input the pivot:
		System.out.print("Please input the pivot: [0 " + "to " + len +"] \n pivot = ");
		
		int pivot = input.nextInt();
		
		//close input scanner
		input.close();
		
		if(pivot == 0) return num; 
		
		//printout the original array:
		printArray(num);
		
		int[] temp = new int[pivot];
		for(int i=0; i<pivot; i++){
			temp[i] = num[i];
		}
		
		for(int i=0; i<len-pivot; i++){
			
			num[i] = num[i+pivot];
		}
		
		for(int i=len-pivot; i<len; i++){
			
			num[i] = temp[i - len+pivot];
		}
		
		
		printArray(num);
		
		return num;
	}

	private static void printArray(int[] num) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<num.length; i++){
			System.out.print(" " + num[i]);
		}
		
		
		System.out.println();
	}
}
