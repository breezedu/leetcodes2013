package leetCode;

import java.util.Arrays;
import java.util.Scanner;

/********************
 * Write an efficient algorithm that searches for a value in an m x n matrix. 
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * 
 * @author Frog
 * 
 * Consider the following matrix:
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50],
 *   Given target = 3, return true.
 */

public class Search2DMatrix {

	public static void main(String[] args){
		
		/******
		 * 1st, create a matrix[][]
		 * the matrix would meet the request of properties;
		 */
		System.out.println("Creat a N*M matrix first. Please input: ");
		System.out.print("N = ");
		Scanner input = new Scanner(System.in);
		
		int N = input.nextInt();
		System.out.print("M = ");
		int M = input.nextInt();
		System.out.print("Target = ");
		int target = input.nextInt();
		
		input.close();
		
		int[][] matrix = new int[N][M];
		int[] arrayList = new int[N*M];
		for(int i=0; i<N*M; i++){
			arrayList[i] = (int) (Math.random()*1000);
		}
		
		Arrays.sort(arrayList);
		
		for(int i=0; i<N; i++){
			
			for(int j=0; j<M; j++){
				
				matrix[i][j] = arrayList[i*M + j];
				
			} // end inner for i<N loop;
			
		} // end outter for i<M loop;
		
		System.out.println("The matrix is: ");
		printMatrix(matrix);
		
		boolean YoN = checkMatrix(matrix, target);
		
		
		if(YoN){
			System.out.println("There is one match in the matrix: " + target);
		} else {
			System.out.println("No such a target.");
		}
		
	} // end of main();

	private static boolean checkMatrix(int[][] matrix, int target) {
		// TODO Auto-generated method stub
		

        int Row = matrix.length;
        int Col = matrix[0].length;
        
        if(target < matrix[0][0] || target > matrix[Row-1][Col-1]){
            return false;
        } // end boundary check;
        
        /****
         * get the Row of the matrix to check;
         * 
         */
        int atRow = 0;
        if(target >= matrix[Row-1][0]){
            atRow = Row-1;
            
        }  else {
            
            for(int i=0; i<Row-1; i++){
                if(target >= matrix[i][0] && target < matrix[i+1][0]){
                    atRow = i;
                }
            } // end for i<Row-1 loop;
        
        } // end if-else condition;
        
        /****
         * after we get the atRow, check every elements at that row;
         */
        for(int j=0; j<Col; j++){
            if(matrix[atRow][j] == target){
                return true;
            }
        } // end for j<Col loop;
        
		return false;
	}

	private static void printMatrix(int[][] matrix) {
		// TODO Printout the matrix;
		
		int Row = matrix.length;
		int Col = matrix[0].length;
		
		for(int i=0; i<Row; i++){
			
			for(int j=0; j<Col; j++){
				
				System.out.print("  " + matrix[i][j]);
			}
			
			System.out.println();
		}
		
	} // end printmatrix() matrix;
	
} // end everything in Search2DMatrix class;
