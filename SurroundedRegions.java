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
		
		//1st, input Row and Col of the matrix;
		System.out.println("This is a Surrounded Regions program.");
		System.out.println("Please input Row and Col of the matrix:");
		Scanner input = new Scanner(System.in);
		
		int Row = input.nextInt();
		int Col = input.nextInt();
		input.close();
		
		//2nd, build a char[][] matrix of random 'O's and 'X's;
		char[][] oxMatrix = buildOXMatrix(Row, Col); //new char[Row][Col];	
		System.out.println("The original maxtrix: ");
		printMatrix(oxMatrix);
		
		//3rd, flap surrounded regions of 'O's;
		flapCaptured(oxMatrix);		// flap the surrounded 'O' to 'X';
		
		//4th and last, printout the matrix after flapping;
		System.out.println("After flapping captured area:");
		printMatrix(oxMatrix);
		
	} // end of main();
	
	
	/*******
	 * build a Row*Col char[][] matrix of 'O' or 'X';
	 * @param Row
	 * @param Col
	 * @return
	 */
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

	/***************
	 * flap all 'O's captured by 'X';
	 * there 4 steps;
	 * 1. build a root matrix;
	 * 2. check boundaries of the oxMatrix, change root matrix boundaries accordingly;
	 * 3. traversal oxMatrix [1 to row-1][1 to col-1], if two 'O's are neighbor, weighted UF root[i][j] and root[m][n];
	 * 4. flap oxMatrix according to the root matrix;
	 * @param oxMatrix
	 */
	private static void flapCaptured(char[][] oxMatrix) {
		// TODO flap captured 'O's in the matrix
		
		if(oxMatrix.length<2 || oxMatrix[0].length<2){
			return;
		} // if there are less than 2 rows or less than 2 columns, just return;
		
		//1st, build a root matrix of row*col;
		int row = oxMatrix.length;
		int col = oxMatrix[0].length;
		
		int[][] root = buildRootMatrix(row, col);
			
		//2nd, check all 4 boundaries, if oxMatrix[i][0] =='O', then root[i][0]=0;
		checkFourBoundaries(root, oxMatrix);
		
		//3rd, check neighbors with Weight Union Find algorithm.
		matrixWUF(root, oxMatrix);
		
		//4th, flap oxMatrix, change surrounded 'O's into 'X's;
		flapMatrix(root, oxMatrix);	
		
	} // end flipCaptured() method;

	/************
	 * flapMatrix() method; 
	 * flap oxMatrix[i][j] according to the ultimate root node of root[i][j];
	 * only flap oxMatrix[i][j] == 'O' nodes;
	 * after the weight union merge 
	 * if root[i][j] pointing to 0; oxMatrix is connecting directly to at least one boundary;
	 * otherwise, if root[i][j] does not point to 0, it belongs to a surrounded area,
	 * under this condition we can flap oxMatrix[i][j] from 'O' to 'X';
	 * 
	 * @param root
	 * @param oxMatrix
	 */
	private static void flapMatrix(int[][] root, char[][] oxMatrix) {
		// TODO flap surrounded 'O' to 'X'
		int row = root.length;
		int col = root[0].length;
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				if(oxMatrix[i][j] == 'O'){
					
					if(findRoot(i, j, root) != 0) {
						oxMatrix[i][j] = 'X'; //if root[i][j]'s ultimate root is not 0, flap it;
						
					} // end inner if condition;
					
				} // end outer if oxMatrix[i][j] == 'O';
			} // end inner for j<col loop;
		} // end outer for i<row loop;
		
		
	} // end flapMatrix() method;

	/************
	 * merge two adjacent nodes by weighted-union-find method;
	 * if two neighbors of oxMatrix are both 'O', merge them;
	 * this method should be renamed to mergeNeighbors();
	 * @param root
	 * @param oxMatrix
	 */
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

	/*************
	 * weighted-union-find two nodes in root matrix: root[i][j] and root[m][n];
	 * when merging only two nodes, 
	 * let the node with bigger ultimate-root point to the one with smaller u-root;
	 * when merging two trees, track back to the two root nodes, 
	 * then compare the two root nodes, let the bigger point to the smaller one;
	 * @param i
	 * @param j
	 * @param m
	 * @param n
	 * @param root
	 */
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

	/**********
	 * find out the ultimate root node of root[i][j];
	 * there are two ways to check out the ultimate root node of root[i][j];
	 * 1, through non-recursion method with a while(root[i][j]!=i*col +j) loop;
	 * 2, through recursion method with a if(root[i][j]!=i*col +j) condition;
	 * both will check the node root[i][j] pointing to (like root[m][n]); 
	 * if root[m][n] pointing to itself, then return root[m][n] as the ultimate root node;
	 * otherwise check root[m][n]'s root, until the ultimate root node;
	 * 
	 * @param i
	 * @param j
	 * @param root
	 * @return
	 */
	private static int findRoot(int i, int j, int[][] root) {
		// TODO Checkout the ultimate root of a node
		int col = root[0].length;
		int rootUlti = root[i][j];
		
		while(root[i][j] != i*col +j){
			
			int temp = root[i][j];
			i = temp/col;
			j = temp%col;
			rootUlti = root[i][j];
		} // Non recursion method to find the ultimate root node;
		
		/********************************************************
		 * either if or while works fine here, :)
		 
		if(root[i][j] != i*col +j){
			
			int m = root[i][j]/col;
			int n = root[i][j]%col;
			rootUlti = findRoot(m, n, root);
		} // Recursion method to find the ultimate root node;
		********************************************************/
		
		return rootUlti;
	} // end findRoot() method;

	/****************
	 * according to the question requirement; the 'O's on the four boundaries do not flap;
	 * so we have to let all 'O' nodes point to the 0 node;
	 * here I use two for loops; one for(i from 0 to col-1); two for(i from 0 to row-1);
	 * with if-condition: if(oxMatrix[i][0] == 'O') root[i][0] = 0;
	 * @param root
	 * @param oxMatrix
	 */
	private static void checkFourBoundaries(int[][] root, char[][] oxMatrix) {
		// TODO Let the 'O's on 4 boundaries point to the [0][0] root
		int row = root.length;
		int col = root[0].length;

		// check the left and right two boundaries;
		for(int i=0; i<row; i++){
			
			if(oxMatrix[i][0] == 'O'){
				root[i][0] = 0;
			} // first col;
			
			if(oxMatrix[i][col-1] == 'O'){
				root[i][col-1] = 0;
			} // last col;
			
		} // end for i<row loop; the 2 col lines have been checked;
		
		// check the up and bottow two boundaries;
		for(int i=0; i<col; i++){
			
			if(oxMatrix[0][i] == 'O'){
				root[0][i] =0;
			} // first row;
			
			if(oxMatrix[row-1][i] == 'O'){
				root[row-1][i] = 0;
			} // last row;
		} // end for i<col loop; the 2 cols have been checked; 
		
	} // end checkFourBoundaries() method;

	/*********************
	 * build a root matrix of row*col;
	 * the value range from 0 to row*col-1;
	 * the value of each root[i][j] means the rootNode of which root[i][j] pointing to;
	 * in other methods, the rootNode would change accordingly;
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
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

	/**********
	 * printout a 2D matrix of characters;
	 * @param oxMatrix
	 */
	private static void printMatrix(char[][] oxMatrix) {
		// TODO Printout the original matrix of char[][]
		if(oxMatrix.length == 0 || oxMatrix[0].length == 0){
			System.out.println("Nothing in the matrix.");
			return;
		}
		
		int row = oxMatrix.length;
		int col = oxMatrix[0].length;
		
		for(int i=0; i<row; i++){
			
			for(int j=0; j<col; j++){
				
				System.out.print(" " + oxMatrix[i][j]);
			} // end inner for j<col loop;
			
			System.out.println();
		} // end outer for i<row loop;
		
		System.out.println();
	} // end printMatrix() method;
	

} // end everything in SurroundedRegions;
