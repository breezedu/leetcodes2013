package leetCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**********
 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
 * reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
 * You must do this in-place without altering the nodes' values.
 * 
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * @author Frog
 *
 */

public class ReorderListQueueStack {

	public static void main(String[] args){
		
		/***********
		 * 1st, create a linked list;
		 */
		System.out.println("This is Reorder List program. Please input the number of nodes:");
		Scanner input = new Scanner(System.in);
		int Num = input.nextInt();
		input.close();
		
		ListNode Head = new ListNode((int)(Math.random()*1000));
		ListNode P = Head;
		for(int i=1; i<Num; i++){
			
			P.next = new ListNode((int)(Math.random()*1000));
			P = P.next;
		} // end for i<Num loop; all nodes created;
		
		printNodes(Head);
		
		Head = reorderList(Head);
		
		System.out.println("After reorder: ");
		
		printNodes(Head);
		
	} // end of main();

	private static ListNode reorderList(ListNode head) {
		// TODO ReOrder the Linked List;
		/****
		 * traversal the linked list;
		 * put every even node into a stack;
		 * combine half of the original list and 
		 * half of the stack back into a new Linked List;
		 * return the new Linked List;
		 */
		//ArrayList<ListNode> arrayNodes = new ArrayList<ListNode>();
		
		Stack<ListNode> stackNodes = new Stack<ListNode>();
		Queue<ListNode> queueNodes = new LinkedList<ListNode>();
		
		ListNode point = head;
		int count = 0;
		
		while(point != null){
	
			stackNodes.push(point);
			queueNodes.add(point);
			count++;
			point = point.next;
			
		} // end while point!=null loop;
				
		/**
		System.out.println("Printout the original list and the stackNodes: " + count/2);
		printNodes(head);
		
		while(!stackNodes.empty()){
			System.out.print(" " + stackNodes.pop().val);
		}
		System.out.println();
		*/

		ListNode newNode = new ListNode(0);
		ListNode p = newNode;
		
		for(int i=0; i<count/2; i++){
			/**********
			 * first step, get a node from the original list
			 * add the node into he new list;
			 */
			p.next = queueNodes.poll();
			p = p.next;
			p.next = null; // the original node has its own reference
			

			/**********
			 * the second step, pop a node from stack;
			 * add the popped node into the new list;				
			 */
			p.next = stackNodes.pop();
			p = p.next;
			p.next = null; // we have to make the reference pointing to null;
				 
		} // end for e:arrayNodes loop;
		
		/*****
		 * if there are even number of nodes in the original list;
		 * then we just get half from origin half from the stack;
		 * Otherwise, we have to get another one from the stack,
		 * which would be the exactly 'middle' node in the original list;
		 */
		if(count%2 != 0){
			p.next = stackNodes.pop();
			p.next.next = null;
		}

		return newNode.next; 
		/*****
		 * change the last line to head = newNode.next;
		 * then it would be accepted by leetcode :)
		 */
		
	} // end reorderNode() method;

	private static void printNodes(ListNode head) {
		// TODO Printout all nodes' value; 
		if(head == null){
			System.out.println("There's no node in the list.");
			return;
		} // end head==null condition;
		
		ListNode p = head.next;
		int Count = 1;
		System.out.print("LinkedList: " + head.val);
		while(p!=null){
			System.out.print(" -> " + p.val);
			p = p.next;
			Count++;
		} // end while p!=null loop;
		
		System.out.println("\nThere are " + Count +" nodes in the list.");
		
	} // end printNodes() method;
	
} // end of everything in ReorderList class;
