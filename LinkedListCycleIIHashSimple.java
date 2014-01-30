package leetCode;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

/*****************************************
 * This ListNode object is the same with LinkedListCycle.java;
 * ***************************************
 * 
 * class ListNode{
 *		int val;
 *		ListNode next;
 *	
 *		ListNode(int x){
 *			val = x;
 *			next = null;
 *		}
 *	} // end of ListNode class;
*/


public class LinkedListCycleIIHashSimple {
	//TODO to find out the starting point of a cycle in a linked list;
	
	public static void main(String[] args){
		/*********
		 * 1st, create a linked list;
		 * use creatNodes() method and Math.random() API;
		 * let the last node.next point to a random index;
		 */
		ListNode Head = creatNodes();
		
		/*************
		 * Printout the list with printNodes() method;
		 * As we are not sure where the cycle begins so far
		 * Use hashSet to check the last node in the list;
		 */
		printNodes(Head);
		
		/*************
		 * check the starting point of the cycle in the list;
		 * using checkStartCycle() method, with hashTable;
		 * print out the index of the node where the cycle start.
		 */
		checkStartCycle(Head);
				
	} // end of main();

	private static void checkStartCycle(ListNode head) {
		// TODO To checkout the starting point of the cycle in the list;
		/***********************
		 * use hashTable to store the node and it's index;
		 * when we get a repeat node, we get the start point of the cycle;
		 */
		if(head == null){
			System.out.println("L57. There's no node.");
			return;
		}

		Hashtable<ListNode, Integer> hashNode = new Hashtable<ListNode, Integer>();
		hashNode.put(head, 0);
		ListNode p = head.next;

		int index = 0;
		
		while(p!=null){
			
			index++;
			if(!hashNode.containsKey(p)){
				
				hashNode.put(p, index);
				
			} else {
				
				System.out.println("\nL76, The start point of the cycle is: " + p.val +", index = " + hashNode.get(p));
				return;
			}
			
			p=p.next;
			
		} // end while p!=null loop;

		System.out.println("\nL84, There's no cycle in the list.");
		
	} // end checkStartCycle() method;
	

	private static void printNodes(ListNode head) {
		// TODO To Print all nodes in the list;
		
		if(head == null){
			System.out.println("L111. There's no node in the list.");
			return;
		}
		/*****
		 * build a hashSet to store all nodes;
		 * when we get a duplicate node, 
		 * that is the start point of the cycle;
		 */
		HashSet<ListNode> hashNodes = new HashSet<ListNode>();
		hashNodes.add(head);
		ListNode p = head.next;
		
		System.out.print("\nPrint Nodes: " + head.val);
		while(p!=null){
			
			if(!hashNodes.contains(p)){
				System.out.print(" -> " + p.val);
				hashNodes.add(p);
				//p=p.next;
				
			} else if(hashNodes.contains(p)){
				System.out.println("\nThere is a cycle.");
				//System.out.println("L215 The cycle begins from index " + hashNodes.get(p));
				break;
			}
				
			p = p.next;
		} // end while;

		if(p==null){
			System.out.println("\nThere's no cycle in the list.");
		}
		System.out.println();
		
	} // end printNodes() method;

	
	private static ListNode creatNodes() {
		// TODO To create a Linked list; The last node pointing to a random index node in the list;
		/*************
		 * input the # of nodes in the linked list;
		 * then creat linked list with random number between 0 and 100;
		 * make the last node pointing to a random node within the # of linked list;
		 */
		System.out.println("Please input how many nodes do you like in the linked list:");
		Scanner inputNum = new Scanner(System.in);
		int Length = inputNum.nextInt();
		
		inputNum.close();
		
		if(Length == 0){
			return null;
		}
		
		ListNode Nodes = new ListNode((int) (Math.random()*100) );
		System.out.print("Creat linked list: \n" + Nodes.val );
		ListNode Pointer = Nodes;
		
		for(int i=1; i<Length; i++){
			Pointer.next = new ListNode( (int) (Math.random()*1000) );
			System.out.print("   " + Pointer.next.val);
			Pointer = Pointer.next;
		} // end of creating nodes; now the last node pointing to null;
		
		//System.out.println("\nLinked list created.");
		
		/*****
		 * Create a random # < Length;
		 * Let the last node point the the random #th of that node
		 */
		int CycleStart = (int) (Math.random() * Length*2);
		
		if(CycleStart < Length){
			
			ListNode p = Nodes;
			
			for(int i=0; i<CycleStart; i++){
				p = p.next;
			} // end for i<CycleStart loop, now p is pointing to the CycleStart-th node in the list;
			
			Pointer.next = p;
			
			System.out.println("\nThe last node pointing to node index : " + CycleStart +". ");
			
		} else {
			
			System.out.println("\nThe last nodes pointing to null.");
		}
		
		System.out.println("Linked list created.");
		
		return Nodes;
	} // end creatNodes() method.
	
	
} // end of LinkedListCycleIISimple.java class;

