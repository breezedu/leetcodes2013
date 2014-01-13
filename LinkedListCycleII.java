package leetCode;

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


public class LinkedListCycleII {
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
		checkStartCycle(Head);
		
		
		
	} // end of main();

	private static void checkStartCycle(ListNode head) {
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
		
		ListNode Pfast=head.next;
		ListNode Pslow=head;
		
		while(Pfast!=Pslow){
			Pfast=Pfast.next.next;
			Pslow=Pslow.next;
			
		} // end while, now Pfast==Pslow;
		ListNode Pmeet = Pslow;
		ListNode Pnew = head;
		//ListNode Pcycle = Pmeet.next;
		int index =0;
		boolean getStartPoint = false;
		
		if(Pmeet == head){
			
			getStartPoint = true;
			System.out.println("The starting point of the cycle is the Head node.");
		} // end if Pmeet == head condition;
		
		while(getStartPoint == false){

			ListNode Pcycle = Pmeet.next;
			while(Pcycle != Pmeet){
				
				if(Pcycle == Pnew){
					System.out.println("\nStarting point of the cycle is: node[" + index +"].");
					getStartPoint=true;
					break;
				} // end if Pcycle == Pnew; 
				
				Pcycle = Pcycle.next;
			} // end inner while; this loop compare Pnew to every node on the cycle;
			
			Pnew = Pnew.next;
			index++;
			
		} // end outer while(getStartPoint==false) loop;
		
		//System.out.println("Done! The starting point is: #" + index +" node.");
		
	} // end checkStartCycle() method;
	

	private static void printNodes(ListNode head) {
		// TODO To printout the val and cycle of the linked list;
		/*********
		 * only when there's no cycle;
		 */
		ListNode Fast = head.next;
		ListNode Slow = head;
		while(Fast != Slow && Slow.next!=null){
			System.out.print(" " + Slow.val + "-->");
			Slow = Slow.next;
			Fast = Fast.next.next;
		}
		ListNode Meet = Slow;
		System.out.print("Meet " + Meet.val); // Meet is the point fast and slow meet;
		
		Slow = Slow.next;
		
		while(Slow != Meet){
			System.out.print("==>" + Slow.val);
			Slow = Slow.next;
		}   
		System.out.println("==>" + Slow.val + " ONE CYCLE from Meet.");
		
	} // end of printNodes() method;

	
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
		
		ListNode Nodes = new ListNode((int) (Math.random()*100) );
		System.out.print("Creat linked list: \n" + Nodes.val );
		ListNode Pointer = Nodes;
		
		for(int i=1; i<Length; i++){
			Pointer.next = new ListNode( (int) (Math.random()*1000) );
			System.out.print("   " + Pointer.next.val);
			Pointer = Pointer.next;
		} // end of creating nodes; now the last node pointing to null;
		
		System.out.println("\nLinked list created.");
		
		/*****
		 * Create a random # < Length;
		 * Let the last node point the the random #th of that node
		 */
		int CycleStart = (int) (Math.random() * Length);
		System.out.println("The index of start point of the cycle is:  " + CycleStart);
		
		/***********
		 * use p to locate the node which is the start point of the cycle;
		 * use q to locate the last node of the list;
		 * then, point q.next=p; close the cycle;
		 */
		ListNode p = Nodes;
		ListNode q = Nodes;
		
		for(int i=0; i<CycleStart; i++){
			p = p.next;
		} // end for i<CycleStart loop, now p is pointing to the CycleStart-th node in the list;
		
		while(q.next!=null){
			q=q.next;
		} // end while q.next!=null, now the q is pointing to the last node in the list;
		q.next = p;
		
		return Nodes;
	} // end creatNodes() method.

	
} // end of LinkedListCycleII.java class;
