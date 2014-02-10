package leetCode;

/*******************
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3,
 * Return [1,3,3,1].
 * 
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */

import java.util.ArrayList;
import java.util.Scanner;

public class PascalTriangleII {
	
	public static void main(String[] args){
		
		// build a Pascal Triangle;
		System.out.println("This is a Pascal's Triangle program.");
		System.out.println("Please input how many rows in the triangle:");
		
		Scanner input = new Scanner(System.in);
		int Num = input.nextInt();
		input.close();
		
		ArrayList<Integer> pascalTriangle = new ArrayList<Integer>();
		
		pascalTriangle = generatePasTriangle(Num);
		
		System.out.println("Printout the numRow of Pascal Triangle:");
		
		printArrayList(pascalTriangle);
		
	} // end main();

	private static void printArrayList(ArrayList<Integer> al) {
		// TODO Auto-generated method stub
		
		for(int c:al){
			System.out.print(" " +c);
		}
		System.out.println();
		
	} // end printArrayList() method;

	private static ArrayList<Integer> generatePasTriangle(int num) {
		// TODO Auto-generated method stub
		ArrayList<Integer> numRow = new ArrayList<Integer>();
			
		int[] tri = new int[num+1]; // the matrix of Pascal triangle;
		
		tri[0] = 1;
		int[] pivot = new int[num+1];
		
	//	ArrayList<Integer> numRow = new ArrayList<Integer>();
		
		if(num == 0){
			numRow.add(tri[0]);
			return numRow;
		}
		
		
		for(int i=1; i<num+1; i++){
			
			tri[0] = 1; 				// the 1st element will always be 1;
			pivot[0] = 1;
			
			for(int j=1; j<=i; j++){

				tri[j] = pivot[j-1] + pivot[j];			
			}
			
			for(int j=1; j<=i; j++){
				pivot[j] = tri[j];
			}
			
		} // end for i<num loop;
		
		for(int i=0; i<num+1; i++){
			numRow.add(pivot[i]);
		}
		
		return numRow;
		
	} // end generatePasTriangle() method;

} // end of everything;
