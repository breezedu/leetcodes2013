package leetCode;

import java.util.Hashtable;
import java.util.Scanner;

/**************
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null. 
 * Return a deep copy of the list.
 * 
 * @author Frog
 *****************
 * Definition for singly-linked list with a random pointer.
 */
class RandomListNode {

	int label;
     RandomListNode next, random;
     RandomListNode(int x) { this.label = x; }
     
 } // end RandomListNode class;

 
public class CopyListWithRandomPointer {
	
	public static void main(String[] args){
		/************************************************
		 * How to build a linked list with two pointers?
		 * 1st, build a normal linked list, with a normal node.next point to it's next node;
		 * then assign a random pointer node.random point to the random node in the list;
		 */
		System.out.println("This is a copy list with Random Pointer program.");
		System.out.println("How many nodes are there in the list?");
		
		Scanner input = new Scanner(System.in);
		
		int Num = input.nextInt();
		input.close();
		System.out.println("L39: Creating randomListNodes.");
		RandomListNode Head = creatRandomList(Num);
		
		System.out.println("\nL42: The list has been created.");
		printRandomList(Head);
		
		/**************************************
		 * Copy the original random linked list to a new List;
		 * Copy the regular and random pointers too;
		 */
		RandomListNode newNodes = copyRandomList(Head);
		System.out.println("\nL50: The list has been copied.");
		printRandomList(newNodes);
		
	} // end main();

	
	/*****************
	 * Copy the original list to a new random-linked-list;
	 * 1st, create a new headNode, then build a parallel list to the original one;
	 * make the new list's label equal to the original list;
	 * then let the oldNode.next pointing to the newNode at the same index;
	 * let the newNode.random pointing to the oldNode at the same index;
	 * 
	 * at the next traversal, let the newNode.random = newNode.random.random.next;
	 * in this way, the newNode.random will travel a circle 
	 * then point to the correct node in the new list.
	 * 
	 * @param head
	 * @return
	 */
	private static RandomListNode copyRandomList(RandomListNode head) {
		// TODO Copy a linked list with a random point;
		RandomListNode p = head;
		RandomListNode retNode = new RandomListNode(0);
		RandomListNode retP = retNode;
		
		while(p!=null){
			
			retP.next = new RandomListNode(p.label);
			retP.next.random = p; // the random pointer of new node points to old node at the same index;
			
			RandomListNode p2 = p;
			p = p.next;
			p2.next = retP.next; // old node's next points to new node at the same index;
			
			retP = retP.next;
			
			//retP.next.random = p2.next.random;
		} // end while (p!=null) loop;

		RandomListNode q = retNode.next;
		while(q!=null){
			q.random = q.random.random.next;
			q = q.next;
		} // end while(q!=null) loop; the copyList's random pointing to the right node now;
		
		return retNode.next;
		
	} // end copyRandomList() method;



	/******************************************************
	 * Printout random Node's label, and it's random pointer's lable val;
	 * @param head
	 */
	private static void printRandomList(RandomListNode head) {
		// TODO Printout the linked list;
		
		RandomListNode p = head;
		while(p!=null){
			System.out.print("  " + p.label);
			System.out.print("->" + p.random.label);
			p = p.next;
		} // end while(p!=null) loop
		
		System.out.println();
	} // end printRandomList() loop;

	
	
	/**********
	 * Create a list of randomListNodes; each node has a node.next pointer pointing to the next node. 
	 * and a random pointer pointing to a random node in the linked list;
	 * This creadRandomList() method will create num of RandomListNodes;
	 * 1st, create a normal list with a regular pointer point to the next node.
	 * Then create an array of random integers, array[i];
	 * at index i's node, array[i] is the index of the node to which node[i]'s random pointer pointing;
	 * 
	 * While building the normal nodes, create a hashTable, to store the index and the node at that index;
	 * after building the normal linked list; traversal the list to assign the random pointer's reference; 
	 * when reach index i; node.random = hashTable.get(array[i]);
	 * 
	 * @param num
	 * @return
	 */
	private static RandomListNode creatRandomList(int num) {
		// TODO Create a linked list with random pointer and regular pointer;
		RandomListNode head = new RandomListNode(0);
		RandomListNode p = head;
		int[] arrayRandom = new int[num];
		Hashtable<Integer, RandomListNode> randomListTable = new Hashtable<Integer, RandomListNode>();
		
		for(int i=0; i<num; i++){
			p.next = new RandomListNode((int)(Math.random()*100)); // this is the val of each node;
			arrayRandom[i] = (int)(Math.random() * num); // this is the index of the node's random pointer pointing to;
			p.next.random = p.next; // Temporally let the random pointing to itself;
			
			randomListTable.put(i, p.next); // put every randomListNode and it's index into a hashTable;
			
			p = p.next;
		} // end for i<num loop; all randomListNode has been created;
		
	//	printRandomList(head.next); // printout the randomListNode before assigning random pointer;
		
		RandomListNode p2 = head.next;
		int index = 0;
		while(p2!=null){
			
			p2.random = randomListTable.get(arrayRandom[index]);
			index++;
			p2 = p2.next;
		}
		
		System.out.println("Printout the index of each node's random pointer pointing to:");
		printArray(arrayRandom);
		
		return head.next;
	} // end creatRandomList() method;
	
	
	/*************************************
	 * Printout an integer array;
	 * @param array
	 */
	private static void printArray(int[] array) {
		// TODO To printout every element in a array;
		
		int Len = array.length;
		for(int i=0; i<Len; i++){
			System.out.print("[" + i+ "->" + array[i] +"]  ");
		}
		System.out.println();
	} // end printArray() method;
	

} // end everything in copy-list-with-random-pointer class;
