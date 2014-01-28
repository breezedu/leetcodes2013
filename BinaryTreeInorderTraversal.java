package leetCode;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**********
 * Given a binary tree, 
 * return the In-Order traversal of its nodes' values.
 * 
 * @author Frog
 */

/*****************
  class TreeNode{
  int val;
  TreeNode left;
  TreeNode right;
  
  	TreeNode(int x){
  		val = x;
  	}
  } // end of TreeNode class;
 
*/

public class BinaryTreeInorderTraversal {
	
	public static void main(String[] args){
		
		/*********
		 * 1st, build an array[] of N elements;
		 * Ask the user to set the length of the array
		 * then create N random numbers;
		 * Put the array into a binary Tree;
		 */
		System.out.println("This is the Binary Tree Inorder Traversal problem.");
		System.out.println("Please input how many Nodes do you like in the tree?");
		
		Scanner input = new Scanner(System.in);
		int Len = input.nextInt();
		input.close();
		
		System.out.println("The array includes: ");
		int[] Nums = new int[Len];
		for(int i=0; i<Len; i++){
			
			Nums[i] = (int) (Math.random() * 1000); 
			System.out.print( Nums[i] +", ");
		} // end for i<Len loop, array created;
		System.out.println();
		
		TreeNode Root = putArrayToTree(Nums);
		
		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		nodeList = inOrderTraversal(Root);
		
		System.out.println("After In Order traversal: ");
		for(int e:nodeList){
			System.out.print(e + ", ");
		}
		
	} // end of main();

	private static ArrayList<Integer> inOrderTraversal(TreeNode root) {
		// TODO To traversal the preOrder Tree;

        ArrayList<Integer> returnList = new ArrayList<Integer>(); // build an ArrayList to store all nodes;
        
        if(root == null){
        	
        	//System.out.println("It's an empty tree.");
        	return returnList;
        	
        } // end if root==null condition;
            
 
        Stack<TreeNode> stack = new Stack<TreeNode>(); // Claim one stack 
      //  stack.push(root);
 
        stack = addTreeToStack(root, stack);
               
        while(!stack.empty()){
        	 
        	TreeNode n = stack.pop();
        	returnList.add(n.val);
        	
        } // end while stack.empty loop;
        
        return returnList;
		
	} // end preorderTraversal() method;

	
	private static Stack<TreeNode> addTreeToStack(TreeNode root, Stack<TreeNode> stack) {
		// TODO Add all nodes in the tree to a stack;
		//Stack<TreeNode> returnStack = new Stack<TreeNode>();
		if (root == null){
			return stack;
			
		} else {

			addTreeToStack(root.right, stack);

			stack.push(root);
		
			addTreeToStack(root.left, stack);
		}
		
		return stack;
	} // end addTreeToStack() method;
	

	private static TreeNode putArrayToTree(int[] nums) {
		// TODO Put all array[i] into a tree
		if (nums.length == 0){
			
			System.out.println("There's no element in the array.");
			return null;
		} // end return null condition;
		
		TreeNode root = new TreeNode(nums[0]); 	// put the first element as the root of the tree;
		int Length = nums.length; 				// the length of the array;
		
		for(int i=1; i<Length; i++){
			// put every nums[i] to the tree;
			
			root = addToTree(root, nums[i]);
		
		} // end for i<Length loop; all elements in the array has been added to the tree;
		
		return root;
		
	} // end of putArrayToTree() method;

	private static TreeNode addToTree(TreeNode root, int N) {
		// TODO Add a node to a tree;
		if(root == null){
			root = new TreeNode(N);
			return root;
			
		}else if(N > root.val && root.right == null){
			
			root.right = new TreeNode(N);
			
		} else if (N > root.val && root.right != null){
			
			addToTree(root.right, N);
			
		} else if (N <= root.val && root.left == null){ // not sure <= is necessary, maybe < is enough;
			
			root.left = new TreeNode(N);
			
		} else if (N<=root.val && root.left != null){
			
			addToTree(root.left, N);
			
		} //end if-else condition; the integer N has been put into the Tree; 
		
		return root;
		
	} // end addToTree method;
	

} // end of everything in BinaryTreeInorderTraversal class;
