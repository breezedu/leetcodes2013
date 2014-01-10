package leetCode;

import java.util.Scanner;
/**************************
 * Add two set of numbers in two different linked lists;
 * @author Frog;
 * Linked List1 = 2->6->4
 * Linked List2 = 5->4->3->7
 * Output List  = 7->0->8->7
 * use pointers to point at each LinkNode;
 * add two of the nodes, if > 10, make next sum+1;
 */

public class AddTwoNumbers1203 {
	
	static AddTwoNumbers1203 ATU = new AddTwoNumbers1203();
	
	/***********
	 * the ListNode class, build List Node objects;
	 * use getVal() method to get the ListNode.val for each newly built node;
	 */
	private class ListNode{
		int val;
		ListNode next;
		
		ListNode(int x){
			val = getVal(x);
			next = null;
		}

		private int getVal(int x) {
			// TO get the val of the node, return x;
			return x;
		}
	} // end of ListNode class;
	
	public static void main(String[] args){
	
		/**************
		 * generate two linked lists, LN1 and LN2;
		 * LN1 and LN2 are heads of each list;
		 * input how many nodes each linked list has;
		 * calculate the sum of each couple nodes in the same index.
		 * if the sum>10, next sum of couple nodes should +1;
		 */
		
		System.out.println("How many nodes do you like in List1 and List2?");
		
		Scanner input1 = new Scanner(System.in);
		int num = input1.nextInt();
		int num2 = input1.nextInt();
		input1.close();
		
		ListNode LN1 = null; // in case there's no element in the linked list :)
		if(num>0){ 
			LN1 = ATU.new ListNode((int) (Math.random()*10));
		} // end if num > 0; the same should be done to LN2;
		
		
		ListNode pointer1 = LN1;	
		for(int i=1; i<num; i++){
			pointer1.next = ATU.new ListNode((int) (Math.random()*10));
			pointer1 = pointer1.next;
		} // end for i< num;
		
		System.out.print("LN1: ");
		printNode(LN1);
		
		ListNode LN2 = null;

		if(num2 > 0){
			LN2 = ATU.new ListNode((int) (Math.random()*10));			
		} // end if num2>0;

		ListNode pointer2 = LN2;

		for(int i=1; i<num2; i++){
			pointer2.next = ATU.new ListNode((int) (Math.random()*10));
			pointer2 = pointer2.next;
		} // end for i<num2;
		
		System.out.print("LN2: ");
		printNode(LN2);
		
		ListNode sumLN = Solution.addTwoNumbers(LN1, LN2);
		
		System.out.print("LNN: ");
		printNode(sumLN);
	}
	
	private static void printNode(ListNode newLN) {
		// TO print each val of a linked list;
		ListNode head = newLN;
		while(head != null){
			System.out.print(head.val + " ");	
			head = head.next;
		}
		
		System.out.println(" ");
	}

	public static class Solution{
		
		public static ListNode addTwoNumbers(ListNode node1, ListNode node2){
			
			int sum = 0;
			int addNext = 0;
			ListNode HeadNode = ATU.new ListNode(0);
			ListNode pointer = HeadNode;
			ListNode pointer1 = node1;
			ListNode pointer2 = node2;
			
			if(node1 == null){
				return node2;
			} else if(node2 == null){
				return node1;
			} // end if-else ;
			
			while(pointer1 != null && pointer2 != null ){
				
			//	System.out.println(" " + node1.val + " " + node2.val +" sum=" + sum +" add=" + addNext);
				
				sum = pointer1.val + pointer2.val + addNext;
				addNext = sum/10; // if node1.val+node2.val >=10, then the next two sum should add 1;
				sum = sum%10;
				
				pointer.val = sum;
						
				pointer1 = pointer1.next;
				pointer2 = pointer2.next;
				
				if(pointer1!=null && pointer2!=null){
					
					pointer.next = ATU.new ListNode(addNext);
					pointer = pointer.next;
				} 
			} // end while node1!=null && node2!=0;
			
			if(pointer1 != null && pointer2 == null){
				if(addNext == 0){
					pointer.next = pointer1;
				} else {
					pointer.next = addTwoNumbers(pointer1, ATU.new ListNode(addNext));
				} // end if else addNext==0;
			} // end if node != null
			else if (pointer2 != null && pointer1 == null){
				if(addNext == 0){
					pointer.next = pointer2;
				} else {
					pointer.next = addTwoNumbers(pointer2, ATU.new ListNode(addNext));
				}
			}  // end if node2 != null, node1==null;
			else if (pointer1 == null && pointer2 == null){
				if(addNext != 0){
					pointer.next = ATU.new ListNode(addNext);
				}
			} // end both node1 and node2 == null;
			
			return HeadNode;
			
		} // end addTwoNumbers class;
	} // end Solution class;
	

} // end all :)
