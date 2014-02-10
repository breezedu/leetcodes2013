package leetCode;

import java.util.ArrayList;
import java.util.Scanner;

public class PascalTriangle {
	
	public static void main(String[] args){
		
		// build a pascal Trangle;
		System.out.println("This is a Pascal's Triangle program.");
		System.out.println("Please input how many rows in the triangle:");
		
		Scanner input = new Scanner(System.in);
		int Num = input.nextInt();
		input.close();
		
		ArrayList<ArrayList<Integer>> pascalTriangle = new ArrayList<ArrayList<Integer>>();
		
		pascalTriangle = generatePasTriangle(Num);
		
		System.out.println("Printout the ArrayList of ArrayList:");
		
		printALofAL(pascalTriangle);
		
	} // end main();

	private static void printALofAL(ArrayList<ArrayList<Integer>> pascalTriangle) {
		// TODO Auto-generated method stub
		
		for(ArrayList<Integer> L:pascalTriangle){
			
			printArrayList(L);
		}
		
	} // end of printAlofAl() method;

	private static void printArrayList(ArrayList<Integer> al) {
		// TODO Auto-generated method stub
		
		for(int c:al){
			System.out.print(" " +c);
		}
		System.out.println();
		
	} // end printArrayList() method;

	private static ArrayList<ArrayList<Integer>> generatePasTriangle(int num) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> Triangle = new ArrayList<ArrayList<Integer>>();
		if(num == 0){
			return Triangle;
		}
		
		int[][] tri = new int[num][num];
		tri[0][0] = 1;
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(tri[0][0]);
		Triangle.add(first);
		
		
		for(int i=1; i<num; i++){
			
			ArrayList<Integer> tempList = new ArrayList<Integer>();
			tri[i][0] = 1; 
			tempList.add(tri[i][0]);
			
			for(int j=1; j<=i; j++){
				tri[i][j] = tri[i-1][j-1] + tri[i-1][j];
				
				tempList.add(tri[i][j]);
			}
			
			Triangle.add(tempList);
			
		} // end for i<num loop;
		
		printMatrix(tri);
		
		return Triangle;
		
	} // end generatePasTriangle() method;

	private static void printMatrix(int[][] tri) {
		// TODO Auto-generated method stub
		
		int row = tri.length;
		for(int i=0; i<row; i++){
			for(int j=0; j<row; j++){
				
				System.out.print(" " + tri[i][j]);
			}
			
			System.out.println();
		}
		
	} // end printMatrix() method;

} // end of everything;
