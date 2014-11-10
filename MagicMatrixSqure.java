package leetCode2014;

import java.util.ArrayList;
import java.util.Scanner;

/**********
 * input a integer,calculate if there's a magic matrix of that integer, make each row and col's sum equal;
 * 
 * take 3 for example, there are 8 such magic matrixes:
 * 
 * @author Frog
 * This one only works for 3*3 matrix, ++!
 */
public class MagicMatrixSqure {
	
	public static void main(String[] args){
		
		System.out.println("This is Magic Matrix Sqre program.");
		
		//1st, ask user to input an integer
		System.out.println("Please input the num (row*col) of the matrix.");
		Scanner input = new Scanner(System.in);
		System.out.print(" num = ");
		int num = input.nextInt();
		input.close();
		
		
		//2nd, call magicMatrix() method to calculate if there's such matrixes;
		ArrayList<int[][]> matrix = magicMatrix(num);
		
		//printout the matrixes in the arrayList
		if(matrix==null || matrix.size()==0) System.out.println("No such magic matrix for " + num);
		else printALofMatrix(matrix);
		
	}//end main();

	private static ArrayList<int[][]> magicMatrix(int num) {
		// TODO build magic matrix, and put all valid matrixes into an arrayList;
		int total = num*num;		
		
		ArrayList<int[][]> matrixAL = new ArrayList<int[][]>();
		ArrayList<ArrayList<Integer>> listSet = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		listSet.add(list);
		
		for(int i=2; i<=total; i++){
			System.out.println("i= " + i);
			ArrayList<ArrayList<Integer>> tempListSet = new ArrayList<ArrayList<Integer>>();
			
			for(int j=0; j<listSet.size(); j++){
				ArrayList<Integer> eachInSet = listSet.get(j);
				
				for(int k=0; k<eachInSet.size()+1; k++){
					ArrayList<Integer> temp = new ArrayList<Integer>(eachInSet);
					temp.add(k, i);
					tempListSet.add(temp);
				}
				
			}//end for j<listSet.size() loop;
			listSet.clear();
			listSet = tempListSet;
			
		}//end for i<=total loop;
		
		//buildMatrixes
		System.out.println("All possible situations has been generated.");
		
		for(int i=0; i<listSet.size(); i++){
			
			ArrayList<Integer> listNums = listSet.get(i);
			int[][] matrix = new int[num][num];
			
			for(int j=0; j<listNums.size(); j++){
				
				int currNum = listNums.get(j);				
				matrix[j/num][j%num] = currNum;
			}
			
			if(isMagicMatrix(matrix))
				matrixAL.add(matrix);
		}
		
		return matrixAL;
	}//end magicMatrix() method;

	private static boolean isMagicMatrix(int[][] matrix) {
		// TODO Check if a matrix is a valid 'magic matrix'
		int row = matrix.length;
		int col = matrix[0].length;
		int averageSum = (row*col+1)*row*col/row/2;
		
		//check each row's sum;
		for(int i=0; i<row; i++){
			int rowSum = 0;
			for(int j=0; j<col; j++){
				rowSum += matrix[i][j];
			}
			
			if(rowSum!=averageSum) return false;
		}
		
		//check each col's sum
		for(int i=0; i<col; i++){
			int colSum = 0;
			
			for(int j=0; j<row; j++){
				colSum += matrix[j][i];
			}
			
			if(colSum!=averageSum) return false;
		}
		
		//check up-left corner to down-right corner
		int objSum = 0;
		for(int i=0; i<row; i++){
			objSum += matrix[i][i];
		}
		if(objSum!=averageSum) return false;
		
		
		//check down-left corner to up-right corner
		objSum = 0;
		for(int j=0; j<col; j++){
			objSum += matrix[row-j-1][j];			
		}
		if(objSum!=averageSum) return false;
		
		return true;
	}//end isMagicMatrix() method;

	private static void printALofMatrix(ArrayList<int[][]> al) {
		// TODO Auto-generated method stub
		
		for(int[][] matrix:al){
			printMatrix(matrix);
		}
		
		System.out.println();
	}//end of printArrayList() method;

	private static void printMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		int row = matrix.length;
		int col = matrix[0].length;
		
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				
				System.out.print("  " + matrix[i][j]);
			}
			
			System.out.println();
		}
		
		System.out.println();
	}//end printMatrix() method;

}//end of everything in MagicMatrixSqure class;
