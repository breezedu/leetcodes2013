package leetCode;

import java.util.Scanner;

/**********
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flapping all 'O's into 'X's in that surrounded region .
 * 
 * @author Frog
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */

public class SurroundedRegions {
	
	public static void main(String[] args){
		
		//1st, build a matrix of 'X' and 'O;
		System.out.println("This is a Surrounded Regions program.");
		System.out.println("Please input Row and Col of the matrix:");
		Scanner input = new Scanner(System.in);
		
		int Row = input.nextInt();
		int Col = input.nextInt();
		input.close();
		
		char[][] oxMatrix = buildOXMatrix(Row, Col); //new char[Row][Col];
				
		System.out.println("The original maxtrix: ");
		printMatrix(oxMatrix);
		
		flapCaptured(oxMatrix);		// flap the surrounded 'O' to 'X';
		
		System.out.println("After flapping captured area:");
		printMatrix(oxMatrix);
		
	} // end of main();
	
	private static char[][] buildOXMatrix(int Row, int Col) {
		// TODO build an OX Matrix[][], assign O or X randomly
		
		char[][] oxMatrix = new char[Row][Col];
		for(int i=0; i<Row; i++){
			for(int j=0; j<Col; j++){
				
				if((int)(Math.random()*3)==1){
					oxMatrix[i][j] = 'O';
					
				} else {
					oxMatrix[i][j] = 'X';
					
				} // end if-else condition;
				
			} // end inner for j<Col loop;
		} // end outer for i<Row loop;
		
		return oxMatrix;
	} // end buildOXMatrix() method;

	private static void flapCaptured(char[][] oxMatrix) {
		// TODO Auto-generated method stub
		int row = oxMatrix.length;
		int col = oxMatrix[0].length;
		
		System.out.println();
		
		int[][] root = buildRootMatrix(row, col);
		//	printMatrix(root); not necessary to printout the original matrix here;
		
		//check all 4 boundaries, if oxMatrix[i][0] =='O', then root[i][0]=0;
		checkFourBoundaries(root, oxMatrix);
		System.out.println("After check all four boundaries, the root matrix is:");
		printMatrix(root);
		
		//check neighbors with Weight Union Find algorithm.
		matrixWUF(root, oxMatrix);
		System.out.println("After weighted Union-Find, the mtrix is:");
		printMatrix(root);
		
		//flap oxMatrix, change surrounded 'O's into 'X's;
		flapMatrix(root, oxMatrix);
		
		
	} // end flipCaptured() method;

	
	private static void flapMatrix(int[][] root, char[][] oxMatrix) {
		// TODO flap surrounded O to X;
		int row = root.length;
		int col = root[0].length;
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				if(oxMatrix[i][j] == 'O'){
					
					if(findRoot(i, j, root) != 0) {
						oxMatrix[i][j] = 'X';
					}
					
				} // end if
			} // end inner for j<col loop;
		} // end outer for i<row loop;
		
		
	} // end flapMatrix() method;

	private static void matrixWUF(int[][] root, char[][] oxMatrix) {
		// TODO check all neighbors when oxMatrix[i][j] == 'O', weighted UF with neighbors
		int row = root.length;
		int col = root[0].length;
		
		for(int i=1; i<row-1; i++){
			
			for(int j=1; j<col-1; j++){
				
				if(oxMatrix[i][j] == 'O'){
					//check up
					if(oxMatrix[i-1][j] == 'O'){
						weightUF(i,j, i-1, j, root);
					}
					
					//check right
					if(oxMatrix[i][j+1] == 'O'){
						weightUF(i, j, i, j+1, root);
					}
					
					//check down
					if(oxMatrix[i+1][j] == 'O'){
						weightUF(i, j, i+1, j, root);
					}
					
					//check left
					if(oxMatrix[i][j-1] == 'O'){
						weightUF(i, j, i, j-1, root);
					}
					
				} // end if oxMatrix[i][j] == O;
			} // end for j<col-1 loop;
		} // end for i<row-1 loop;
		
		
	} // end matrixWUF() method;

	private static void weightUF(int i, int j, int m, int n, int[][] root) {
		// TODO weighted union two nodes; merge to the small root
		int col = root[0].length;
		int root1 = findRoot(i, j, root); // call findRoot() method to check the ultimate root
		int root2 = findRoot(m, n, root);
		
		if(root1 > root2){
			root[root1/col][root1%col] = root2;
			
		} else if(root2 > root1) {
			root[root2/col][root2%col] = root1;
			
		} // end if-else conditions;
		
		
	} // end weightUF() method;

	private static int findRoot(int i, int j, int[][] root) {
		// TODO Checkout the ultimate root of a node
		int col = root[0].length;
		int rootUlti = root[i][j];
		
		if(root[i][j] != i*col +j){
			int m = root[i][j]/col;
			int n = root[i][j]%col;
			rootUlti = findRoot(m, n, root);
		} // Recursion
		
		return rootUlti;
	} // end findRoot() method;

	private static void checkFourBoundaries(int[][] root, char[][] oxMatrix) {
		// TODO Let the 'O's on 4 boundaries point to the [0][0] root
		int row = root.length;
		int col = root[0].length;

		for(int i=0; i<row; i++){
			
			if(oxMatrix[i][0] == 'O'){
				root[i][0] = 0;
			} // first col;
			
			if(oxMatrix[i][col-1] == 'O'){
				root[i][col-1] = 0;
			} // last col;
			
		} // end for i<row loop; the 2 col lines have been checked;
		
		for(int i=0; i<col; i++){
			
			if(oxMatrix[0][i] == 'O'){
				root[0][i] =0;
			} // first row;
			
			if(oxMatrix[row-1][i] == 'O'){
				root[row-1][i] = 0;
			} // last row;
		} // end for i<col loop; the 2 cols have been checked; 
		
	} // end checkFourBoundaries() method;

	private static int[][] buildRootMatrix(int row, int col) {
		// TODO build a row*col matrix, with consecutive order from 0 to row*col
		int[][] root = new int[row][col];
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				root[i][j] = i*col +j;
				
			} // end inner for i<col loop;
			
		} // end outer for i<row loop;
		
		return root;
		
	} // end buildRootMatrix() method;

	private static void printMatrix(int[][] root) {
		// TODO Printout a 2D matrix of integers;
		int row = root.length;
		int col = root[0].length;
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				if(root[i][j]<10){
					System.out.print("   " + root[i][j]);
					
				} else if(root[i][j]<100){
					System.out.print("  " + root[i][j]);
					
				} else {
					System.out.print(" " + root[i][j]);
					
				} // end if-else conditions; 

			} // end for j<col loop;
			
			System.out.println();
		} // end for i<row loop;
		
	}// end printMatrix(int[][]) method;

	private static void printMatrix(char[][] oxMatrix) {
		// TODO Printout the original matrix of char[][]
		int row = oxMatrix.length;
		int col = oxMatrix[0].length;
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				System.out.print(" " + oxMatrix[i][j]);
			} // end inner for j<col loop;
			
			System.out.println();
		} // end outer for i<row loop;
		
	} // end printMatrix() method;
	

} // end everything in SurroundedRegions;
