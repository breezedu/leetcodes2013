package leetCode;

import java.util.Scanner;

/*********
 * Sort a linked list using merge sort.
 * 
 * @author Frog
 *
 * use recursion to divide the original list into 2, then 4 ....
 * untill only 1 node left in the sub-list;
 * after dividing, merge the divided two lists together;
 * for the very first merging operation, merge only two nodes;
 * after that, every sub-list is sorted;
 * so the merge method would merge not only nodes, but also lists;
 */

/*
class ListNode{
	int val;
	ListNode next;
	
	ListNode(int x){
		val = x;
		next = null;
	}
} //end of ListNode class;

*/

public class MergeSortLinkedList {

	public static void main(String[] args){
		
		/*****
		 * 1st, create a linked list;
		 * with a random value to each Linked Node;
		 */
		System.out.println("This is a program to sore a linked list using merge sort.");
		System.out.println("Please tell us how many Nodes do you like in the list?");
		
		Scanner input = new Scanner(System.in);
		int NumOfNodes = input.nextInt();
		input.close();
		
		
		ListNode Head = new ListNode((int)(Math.random()*1000));
		
		if(NumOfNodes == 0){
			Head = null;
		} // end if input == 0 condition;
		
		ListNode Pointer = Head;
		
		for(int i=1; i<NumOfNodes; i++){
			
			Pointer.next = new ListNode((int)(Math.random()*1000));
			Pointer = Pointer.next;
		} // end for i<NumOfNodes loop; all nodes created;
		
		System.out.println("The linked listed has been created. \nPrinting the original list:");
		printList(Head);
		
		/************
		 * Merge sort a list;
		 * 1st to divide the list;
		 * then merge them together;
		 */
		Head = divideThenMerge(Head);
		
		System.out.println("\nAfter merge sorting:");
		printList(Head);
		
	} // end of main();
	

	private static ListNode divideThenMerge(ListNode head) {
		// TODO Divide and Sort the linked list;
		
		if(head.next == null || head == null){
			return head;
		}
		
		ListNode Slow = head;
		ListNode Fast = head;
		
		while(Fast.next != null && Fast.next.next != null){
			Slow = Slow.next;
			Fast = Fast.next.next;
		} // end while loop. now Fast.next.next == null;
		
		Fast = Slow.next; 	// let Fast pointing to the middle of the list;
		Slow.next = null; 	// break the list into two lists; 
		Slow = head;		// Now Slow pointing to the 1st half, Fast pointing to the later half;
		
		Slow = divideThenMerge(Slow);
		Fast = divideThenMerge(Fast);
		
		ListNode temp = mergeList(Fast, Slow);
		
		return temp;
		
	} // end divideThenMerge() method;


	private static ListNode mergeList(ListNode fast, ListNode slow) {
		// TODO To merge two sorted Lists;
		if(fast == null){
			return slow;	
		} 
		if(slow == null){
			return fast;
			
		} // end if fast or slow ==null condition;
		
		ListNode P = new ListNode(0);
		ListNode newList = P;
		
		while(fast!=null && slow!=null){

			if(fast.val < slow.val){
				P.next = fast;
				fast = fast.next;
				P = P.next;
				
			} else {
				
				P.next = slow;
				slow = slow.next;
				P = P.next;
			}
			
		} // end while both fast and slow != null;
		
		if(fast == null){
			P.next = slow;
		} else {
			P.next = fast;
		} // end fast or slow == null condition;
		
		return newList.next;
		
	} // end of mergeList() method;

	
	private static void printList(ListNode head) {
		// TODO Print out every node in the list;
		
		if(head == null){
			System.out.println("There's no node in the list.");
			return;
		} // end of empty list;
		
		System.out.print(head.val);
		
		ListNode P = head.next;
		int Num = 1;
		while(P!=null){
			System.out.print(" -> " + P.val);
			P = P.next;
			Num++;
		} // end whild P!=null loop;
		
		System.out.println("\nThere are " + Num +" nodes in the linked list.");
		
	} // end printList() method;
	
	
} // end of everything in MergeSortLinedList class.
