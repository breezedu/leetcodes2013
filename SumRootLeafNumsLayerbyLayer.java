package leetCode;

import java.util.Scanner;

/****************
 * Given a binary tree containing digits from 0-9 only, 
 * each root-to-leaf layer could represent a number layer.
 * Sum up all layers down to the leaf:
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * another example: 3->4, 3>1, 4->6, 4->3, is a 3-layer tree:
 * the treeSum is 3*100 + 4*10 + 1*10 + 3 + 6 = 359;
 * Find the total sum of all root-to-leaf numbers.
 * 
 * @author Frog
 * 
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class SumRootLeafNumsLayerbyLayer {

	public static void main(String[] args){
		
		// 1st, we have to build a tree, from an array;
		
		System.out.println("This is a Sum Root to Leaf Numbers program.");
		System.out.println("Please input how many nodes do you like in the tree: ");
		
		Scanner input = new Scanner(System.in);
		int Num = input.nextInt();
		input.close();
		
		int[] nodeArray = new int[Num];
		for(int i=0; i<Num; i++){
			
			nodeArray[i] = (int) (Math.random()*10);
		} // end for i<Num loop; all elements in the array has been assigned an value;
		
		System.out.println("Printout the array:");
		printArray(nodeArray);
		
		// put the array into a tree;
		
		TreeNode root = buildTree(nodeArray);
		
		System.out.println("The Tree has been built. root= " + root.val +" left=" + root.left +". right=" + root.right);
		
		int depTree = deepTree(root);
		System.out.println("The depth of the tree is: "+depTree +".");
		
		int sum = sumNumbers(root);
		
		System.out.println("The sum is: " + sum);
		
	} // end main();

	private static int sumNumbers(TreeNode root) {
		// TODO Calculate the tree-sum of each node;
		if(root == null){
			return 0;
		}
		
		int dep = deepTree(root);
		
		int sum = root.val;
		for(int i=1; i<dep; i++){
			sum *= 10;
		}
		
		if(root.left!=null || root.right!=null){
			sum = sum + sumNumbers(root.left) + sumNumbers(root.right);
		}
		
		return sum;
		
	} // end sumNumbers() method.

	
	private static int deepTree(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null){
			return 0;
		}
		int dep = 1;
		if(root.left!=null || root.right!=null){
			dep = dep + Math.max(deepTree(root.left), deepTree(root.right));
		}
		
		return dep;
	} // end deepTree() method;

	private static void printArray(int[] array) {
		// TODO Printout an array;
		int Len = array.length;
		for(int i=0; i<Len; i++){
			
			System.out.print(" " + array[i]);
		}
		
		System.out.println();
		
	} // end printArray() method;

	
	private static TreeNode buildTree(int[] nodeArray) {
		// TODO Put every elementary in the array into a tree;
		int Len = nodeArray.length;
		if(Len == 0){
			return null;
		} // end empty tree condition;
		
		TreeNode retRoot = new TreeNode(nodeArray[0]);
		for(int i=1; i<Len; i++){
			
			retRoot = addNode(nodeArray[i], retRoot);
		} // end for i<Len loop, addNode()
		
		return retRoot;
	}

	private static TreeNode addNode(int n, TreeNode retRoot) {
		// TODO Auto-generated method stub
		if(retRoot == null){
			retRoot = new TreeNode(n);
			
		} else if(n>= retRoot.val && retRoot.right==null){
			retRoot.right = new TreeNode(n);
			
		} else if(n>= retRoot.val && retRoot.right!=null){
			addNode(n, retRoot.right);
			
		} else if(n< retRoot.val && retRoot.left==null){
			retRoot.left = new TreeNode(n);
			
		} else {
			addNode(n, retRoot.left);
		}
		
		return retRoot;
		
	} // end of addNode() method;
	
	
} // end everything in SumRootLeafNums class;
