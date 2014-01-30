package leetCode;

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


public class LinkedListCycleIIHash {
	//TODO to find out the starting point of a cycle in a linked list;
	
	public static void main(String[] args){
		/*********
		 * 1st, create a linked list;
		 * use creatNodes() method and Math.random() API;
		 * let the last node.next point to a node in the list with random index;
		 */
		ListNode Head = creatNodes();
		
		/*************
		 * Printout the list with printNodes() method;
		 * As we are not sure where the cycle begins so far
		 * Just print out the cycle as a repeat;
		 */
		printNodes(Head);
		
		/*************
		 * check the starting point of the cycle in the list;
		 * with checkStartCycle() method, 
		 * print out the index of the node where the cycle start.
		 */
		ListNode cycleBegin = checkStartCycle(Head);
		
		if(cycleBegin != null){
			System.out.println("L48. The start point of the cycle is: " + cycleBegin.val);
		} else {
			System.out.println("L50. There's no cycle.");
		}

		
	} // end of main();

	private static ListNode checkStartCycle(ListNode head) {
		// TODO To checkout the starting point of the cycle in the list;
		/***********************
		 * create two pointers, PFast=Head.next, PSlow=Head; 
		 * PFast=PFast.next.next, PSlow=PSlow.next;
		 * Thus, the PFast will meet PSlow in the cycle. Mark this meet point the the PMeet;
		 * Create another two pointers, PNew=Head, PCycle=PMeet;
		 * PNew=PNew.next, PCycle=PCycle.next && PCycle!=PMeet, PNew will go through the List;
		 * PCycle will just go through the cycle.
		 * 
		 * for every PNew in the list:
		 * 		if PCycle == PNew, this is the starting point;
		 * 		else PCycle=PCycle.next && PCycle!=PMeet;
		 * 		PNew = PNew.next;
		 * 
		 * Printout the index when PCycle = PNew;
		 */
		if(head == null){
			return null;
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
				
				System.out.println("\nL91, The start point of the cycle is " + p.val +", index=" + hashNode.get(p));
				return p;
			}
			
			p=p.next;
			
		} // end while p!=null loop;

		System.out.println("L97, There's no cycle in the list.");
		return p;
		
	} // end checkStartCycle() method;
	

	private static void printNodes(ListNode head) {
		// TODO To printout the val and cycle of the linked list;
		/*********
		 * only when there's no cycle;
		 */
		boolean Cycle = checkCycle(head);
		
		if(Cycle == false){
			
			System.out.println("\nL131: Printout a linked list without cycle.");
			printNodesLine(head);
			
		} // end if Cycle==false condition;
		
		if(Cycle){
			
			System.out.println("\nPrintout a linked list with cycle.");
			printNodesCycle(head);
			
		} // end if cycle==true conditon;
		
	} // end of printNodes() method;

	private static void printNodesCycle(ListNode head) {
		// TODO To printout a linked list with a cycle inside;
		ListNode fast = head;
		ListNode slow = head;
		int meet = 0;
		System.out.print(" " + head.val );
		
		while(meet < 3){
			
			if(fast == slow){
				meet++;
			}
			
			if(meet < 2){
				System.out.print(" -> " + slow.next.val);
				
			} else {
				System.out.print(" => " + slow.next.val);
			}
			
			fast = fast.next.next;
			slow = slow.next;
			
		} // end while meet<2 loop;

		System.out.println();
		
	} // end printNodesCycle() method;

	private static void printNodesLine(ListNode head){
		// TODO To printout a linked list without a cycle;
		if(head == null){
			System.out.println("there's no node.");
			return;
		}
		
		System.out.print(head.val +" ");
		ListNode p = head.next;
		
		while(p!=null){
			System.out.print(" -> " + p.val);
			p = p.next;
		}
		System.out.println();
		
	} // end printNodesLine() method;
	
	
	private static boolean checkCycle(ListNode head) {
		// TODO To check if there's a cycle in the linked list;
		if(head == null){
			System.out.println("L176. There's no node in the list.");
			return false;
		}
		/*****
		 * build a hashtable to store all nodes;
		 * when we get a duplicate node, 
		 * that is the start point of the cycle;
		 */
		Hashtable<ListNode, Integer> hashNodes = new Hashtable<ListNode, Integer>();
		int index = 0;
		hashNodes.put(head, index);
		ListNode p = head.next;
		
		while(p!=null){
			
			index++;
			
			if(!hashNodes.containsKey(p)){
				
				hashNodes.put(p, index);
				//p=p.next;
				
			} else if(hashNodes.containsKey(p)){
				
				//System.out.println("L215 The cycle begins from index " + hashNodes.get(p));
				return true;
			}
				
			p = p.next;
		} // end while;
		
		return false; // if the last node points to null; return false;
		
	} // end checkCycle() method;

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
			ListNode q = Nodes;
			
			for(int i=0; i<CycleStart; i++){
				p = p.next;
			} // end for i<CycleStart loop, now p is pointing to the CycleStart-th node in the list;
			
			while(q.next!=null){
				q=q.next;
			} // end while q.next!=null, now the q is pointing to the last node in the list;
			q.next = p;
			
			System.out.println("\nThe last node pointing to node index : " + CycleStart +". ");
			
		} else {
			
			System.out.println("\nThe last nodes pointing to null.");
		}
		
		/***********
		 * use p to locate the node which is the start point of the cycle;
		 * use q to locate the last node of the list;
		 * then, point q.next=p; close the cycle;
		 */
		System.out.println("Linked list created.");
		
		return Nodes;
	} // end creatNodes() method.
	
	
} // end of LinkedListCycleII.java class;

