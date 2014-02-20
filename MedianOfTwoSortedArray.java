package leetCode;

import java.util.Scanner;

public class MedianOfTwoSortedArray { 
		
	public static void main(String[] args){
		
		System.out.println("This is a Median of Two Sorted Array program.");
		
		//1st, build 2 arrays
		System.out.println("Please input the length of each array:");
		Scanner input = new Scanner(System.in);
		System.out.print("num1 = " );
		int num1 = input.nextInt();
		System.out.print("num2 = " );
		int num2 = input.nextInt();
		input.close();
		
		int arrayOne[] = createArray(num1);
		int arrayTwo[] = createArray(num2);
		//printout two arrays
		printArray(arrayOne);
		printArray(arrayTwo);
		
		//3rd, checkout the median element of the two arrays;
		//int[] arrayA = {1, 3, 4, 6, 7, 9, 24, 49};
		//int[] arrayB = {4, 6, 8, 12, 23, 56, 68};
		
		int Median = findMedianSortedArrays(arrayOne, arrayTwo);
		System.out.println("The median is: " + Median);
		
	}//end main();
	
	private static void printArray(int[] array) {
		// TODO Printout an array
		if(array==null){
			System.out.println("Nothing in the array.");
			return;
		}
		
		int Len = array.length;
		for(int i=0; i<Len; i++){
			
			System.out.print(" " + array[i]);
		}
		
		System.out.println();
		
	}//end of printArray() method

	private static int[] createArray(int num) {
		// TODO create an sorted array
		
		if(num==0) return null;
		int[] array = new int[num];
		
		array[0] = (int)(Math.random() * 25);
		for(int i=1; i<num; i++){
			array[i] = array[i-1] + (int)(Math.random() * 25);
		}
		
		return array;
		
	}//end createArray() method;

	//very much like the merge-sort; but we just merge them till reach median of the new array;
	public static int findMedianSortedArrays(int A[], int B[]){
			
		if(A==null){ return B[B.length/2];	}			
		if(B==null){ return A[A.length/2];	}
			
		int Lena = A.length;
		int Lenb = B.length;
			
		int pa = 0;
		int pb = 0;
		int pc = 0;
			
		//what we really need, is the (Lena+lenb)/2 TH elements in the merged Array;
			
		while(pc < (Lena+Lenb)/2){
			System.out.print(" (pc=" + pc +", pa=" +pa +", pb=" + pb+")");
				
			if(pa>= Lena){
				System.out.println(" " +B[pb]);
				pb++;
			
			} else if(pb>= Lena){
				System.out.println(" " +A[pa]);
				pa++;
					
			} else if(A[pa] < B[pb]){
				System.out.println(" " +A[pa]);
				pa++;
					
			} else if(B[pb] <= A[pa]){
				System.out.println(" " +B[pb]);
				pb++;
					
			}
				
			pc++;
		}//end while() 
			
		if(pa>=Lena) return B[pb]; //if pa has reached the end of A[], just return B[pb];
		if(pb>=Lenb) return A[pa];
			
		return Math.min(A[pa], B[pb]); //if neither reached the end, return smaller;
			
	}// end of findMedianSortedArrays() method;

}//end everything in MedianOfTwoSortedArray class
