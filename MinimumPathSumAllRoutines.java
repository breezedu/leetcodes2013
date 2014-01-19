package leetCode;

import java.util.ArrayList;
import java.util.Scanner;



/******************************************************************
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right 
 * which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * 
 * @author Frog :)
 *
 */


/**********************
 * Pairs object, used to storage the Row and Col indices of a node;
 * In this program, when back tracking the routine, if the nodes fell
 * into the minimum routine, storage the indices into an ArrayList;
 * when printing the routine, just printed the nodes stored in the AL;
 */
class Pairs{
	int Row;
	int Col;
} // end Pair class;



public class MinimumPathSumAllRoutines {
	
	public static void main(String[] args){
		/*******
		 * 1st, build a matrix;
		 * input the Row and Col from command line;
		 * here I would like to use Scanner(System.in);
		 */
		System.out.println("This program solve the MinPathProblem.");
		System.out.println("Please input the Row and Col of the matrix:");
		
		Scanner input = new Scanner(System.in);
		int Row = input.nextInt();
		int Col = input.nextInt();
		input.close();
		
		int[][] mapMatrix = buildMapMatrix(Row, Col);
		
		System.out.println("Printout the map matrix:");
		printMatrix(mapMatrix);
		
		/******
		 * build a minPath matrix;
		 * 1st Row and 1st Col pointing to the mapMatrix[0,0];
		 * other minPath[i,j] = mapMatrix[i,j] + min(minPath[i-1,j], minPath[i,j-1];
		 * till the right-down element;
		 */
		int[][] minPathMatrix = buildPathMatrix(mapMatrix);
		System.out.println("Printout the minPathMatrix:");
		printMatrix(minPathMatrix);
		
		/************
		 * build a backTracking Matrix;
		 * if pathMatrix[i,j] = pathMatrix[i-1,j] + mapMatrix[i,j]
		 * 		backTrack[i,j] = 'L'; L for Left;
		 * if pathMatrix[i,j] = pathMatrix[i,j-1] + mapMatrix[i,j]
		 * 		backTrack[i,j] = 'U'; U for Up;
		 * first Row all pointing to Zero, so all are 'L';
		 * first Col all pointing to Zero, so all are 'U';
		 */
		char[][] backTrackMatrix = buildBackTrackMatrix(minPathMatrix, mapMatrix);
		System.out.println("Printout the back tracking matrix:");
		printMatrix(backTrackMatrix);
		
		/******
		 * printout the routine of the minimum path sum;
		 * show the routine in the mapMatrix;
		 * 
		 * make the [Row, Col] of each node as a Pair object;
		 * Following the backTracking Matrix, storage the Pairs into an ArrayList;
		 * Printout the ArrayList when the routine reaches the [0,0] node;
		 */
		System.out.println("\nThe shortest routine sums up to: " + minPathMatrix[Row-1][Col-1] + " weight.");

		ArrayList<Pairs> routinePairs = new ArrayList<Pairs>();
		
		printMinPathRoutine(backTrackMatrix, mapMatrix, Row-1, Col-1, routinePairs);
		
		System.out.print("\nDone :)");
				
	} // end main();
	

	private static void printMinPathRoutine(char[][] backTrackMatrix, int[][] mapMatrix, int row, int col, ArrayList<Pairs> routinePairs) {
		// TODO Printout the routine of minimum path; show the routine in the mapMatrix;
		if(row==0 && col==0){
			
			Pairs newPair = new Pairs();
			newPair.Row = row;
			newPair.Col = col;
			routinePairs.add(newPair);
			
			System.out.print("\n Got one routine:");
			
			printRoutine(routinePairs);
			
			return;
			
		} else if(backTrackMatrix[row][col] == 'B'){
			
			Pairs newPair = new Pairs();
			newPair.Row = row;
			newPair.Col = col;
			routinePairs.add(newPair);
			
			ArrayList<Pairs> routineUp = new ArrayList<Pairs>();
			ArrayList<Pairs> routineLeft = new ArrayList<Pairs>();
			for(Pairs P:routinePairs){
				routineUp.add(P);
				routineLeft.add(P);
			
			}// end for Pairs P; all nodes added to both up and down arrayLists;
			
			/*******
			 * when the character cased 'B', it means there are *2 routines
			 * one pointing back up, the other pointing back left;
			 * so, in the following recursion call, two printMinPathRoutine() methods
			 * will be called: up and left;
			 */
			printMinPathRoutine(backTrackMatrix, mapMatrix, row, col-1, routineLeft);
			
			printMinPathRoutine(backTrackMatrix, mapMatrix, row-1, col, routineUp);

		} else if(backTrackMatrix[row][col] == 'L'){
			
			Pairs newPair = new Pairs();
			newPair.Row = row;
			newPair.Col = col;
			routinePairs.add(newPair);
			
			printMinPathRoutine(backTrackMatrix, mapMatrix, row, col-1, routinePairs);


		} else if(backTrackMatrix[row][col] == 'U'){
			
			Pairs newPair = new Pairs();
			newPair.Row = row;
			newPair.Col = col;
			routinePairs.add(newPair);
			
			printMinPathRoutine(backTrackMatrix, mapMatrix, row-1, col, routinePairs);


		} // end if-else loop;
		
	} // end printMinPathRoutine() method;

	private static void printRoutine(ArrayList<Pairs> routinePairs) {
		// TODO Printout all elements in the ArrayList<Pairs>
		for(Pairs P:routinePairs){
			
			System.out.print(" [" + P.Row + "," + P.Col +"]");
		} // end for loop;
		
	} // end of printRoutinePairs() method;

	private static char[][] buildBackTrackMatrix(int[][] minPathMatrix,	int[][] mapMatrix) {
		// TODO To build a backTracking matrix;
		/****
		 * 'U' = Up; 'L' = Left; 'B' = Both;
		 */
		
		int row = mapMatrix.length;
		int col = mapMatrix[0].length;
		
		char[][] backTrackMatrix = new char[row][col];
		
		backTrackMatrix[0][0] = 'O';
		
		for(int i=1; i<row; i++){
			backTrackMatrix[i][0] = 'U';
		} // end for i<row loop; first col assigned;
		
		for(int j=1; j<col; j++){
			backTrackMatrix[0][j] = 'L';
		} // end for j<col loop; first row assigned;
		
		for(int i=1; i<row; i++){
			
			for(int j=1; j<col; j++){
				
				if( minPathMatrix[i][j] == mapMatrix[i][j] + minPathMatrix[i-1][j] &&
					minPathMatrix[i][j] == mapMatrix[i][j] + minPathMatrix[i][j-1]	){
					backTrackMatrix[i][j] = 'B';
					
				} else if(minPathMatrix[i][j] == mapMatrix[i][j] + minPathMatrix[i-1][j]){
					backTrackMatrix[i][j] = 'U';
				} else {
					
					backTrackMatrix[i][j] = 'L';
				} // end else-if condition;
				
				
			} // end inner for i<col loop;
		} // end outer for i<row loop; all elements assigned;
		
		return backTrackMatrix;
	} // end buildBackTrackMatrix() method;

	private static int[][] buildPathMatrix(int[][] mapMatrix) {
		// TODO Build the pathMatrix;
		
		int row = mapMatrix.length;
		int col = mapMatrix[0].length;
		int[][] minPathMatrix = new int[row][col];
		
		minPathMatrix[0][0] = mapMatrix[0][0]; // first element = mapMatrix[0][0];
		
		for(int i=1; i<row; i++){
			minPathMatrix[i][0] = mapMatrix[i][0] + minPathMatrix[i-1][0];
		} // end for i<row; first col assigned;
		
		for(int j=1; j<col; j++){
			minPathMatrix[0][j] = mapMatrix[0][j] + minPathMatrix[0][j-1];
		} // end for j<col loop; first row assigned;
		
		for(int i=1; i<row; i++){
			
			for(int j=1; j<col; j++){
				
				minPathMatrix[i][j] = mapMatrix[i][j] + minOfTwo(minPathMatrix[i-1][j], minPathMatrix[i][j-1]);
			} // end inner for i<col loop;
			
		} // end outer for i<row loop;
		
		return minPathMatrix;
		
	} // end buildPathMatrix() method;

	private static int minOfTwo(int i, int j) {
		// TODO Return the minimum value of two integers;
		int Min = i;
		if(j<Min)
			Min = j;
		
		return Min;
		
	} // end minOfTwo() method;

	/*******
	 * Printout the matrix, line by line;
	 * @param matrix
	 */
	private static void printMatrix(int[][] matrix) {
		// TODO Printout the int[][] matrix;
		int row = matrix.length;
		int col = matrix[0].length;
		
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				
				System.out.print(" " + matrix[i][j]);
				
			} // end inner loop;
			
			System.out.println();
		} // end outer for loop; matrix printed;
		
	} // end printMatrix(int[][]) method;
	
	private static void printMatrix(char[][] matrix) {
		// TODO Printout the char[][] matrix;
		int row = matrix.length;
		int col = matrix[0].length;
		
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				
				System.out.print(" " + matrix[i][j]);
				
			} // end inner loop;
			
			System.out.println();
		} // end outer for loop; matrix printed;
		
	} // end printMatrix(char[][]) method;

	
	/******
	 * build a row*col matrix;
	 * each element assigned with a random value (0-100);
	 * @param row
	 * @param col
	 * @return
	 */
	private static int[][] buildMapMatrix(int row, int col) {
		// TODO Build a map matrix; 
		
		int[][] Matrix = new int[row][col];
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				Matrix[i][j] = (int) (Math.random()*10);
				
			} // end inner for loop;
		} // end outer for loop; matrix created;
		
		return Matrix;
		
	} // end buildmapMatrix() method;
	
	
} // end of MinimumPathSum class;
