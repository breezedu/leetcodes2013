package leetCode;

import java.util.Scanner;

public class AddTwoNumbers {
	
	static AddTwoNumbers ATU = new AddTwoNumbers();
	
	private class ListNode{
		int val;
		ListNode next;
		
		ListNode(int x){
			val = x;
			next = null;
		}
	} // end of ListNode class;
	
	public static void main(String[] args){
	
		/**************
		 * generate two linked lists, LN1 and LN2;
		 * LN1 and LN2 are heads of each list;
		 */
		ListNode LN1 = ATU.new ListNode(2);
		ListNode pointer1 = LN1;
		System.out.println("How many nodes do you like in List1 and List2?");
		
		Scanner input1 = new Scanner(System.in);
		int num = input1.nextInt();
		int num2 = input1.nextInt();
		input1.close();
		
		for(int i=1; i<num; i++){
			pointer1.next = ATU.new ListNode((int) (Math.random()*10));
			pointer1 = pointer1.next;
		}

		printNode(LN1);

		ListNode LN2 = ATU.new ListNode((int) (Math.random()*10));
		ListNode pointer2 = LN2;

		for(int i=1; i<num2; i++){
			pointer2.next = ATU.new ListNode((int) (Math.random()*10));
			pointer2 = pointer2.next;
		}
		printNode(LN2);
		
		
		ListNode sumLN = Solution.addTwoNumbers(LN1, LN2);
		
		printNode(sumLN);
	}
	
	private static void printNode(ListNode newLN) {
		// TO print each val of a linked list;
		ListNode head = newLN;
		while(head != null){
			System.out.print(head.val + " ");	
			head = head.next;
		}
		
		System.out.println(" Done printing.");
	}

	public static class Solution{
		
		public static ListNode addTwoNumbers(ListNode node1, ListNode node2){
			
			int sum = 0;
			int addNext = 0;
			ListNode HeadNode = ATU.new ListNode(0);
			ListNode pointer = HeadNode;
			
			if(node1 == null){
				return node2;
			} else if(node2 == null){
				return node1;
			} // end if-else ;
			
			while(node1 != null && node2 != null ){
				
			//	System.out.println(" " + node1.val + " " + node2.val +" sum=" + sum +" add=" + addNext);
				
				sum = node1.val + node2.val + addNext;
				addNext = sum/10; // if node1.val+node2.val >=10, then the next two sum should add 1;
				sum = sum%10;
				
				pointer.val = sum;
						
				node1 = node1.next;
				node2 = node2.next;
				if(node1!=null && node2!=null){
					
					pointer.next = ATU.new ListNode(addNext);
					pointer = pointer.next;
				} 
			} // end while node1!=null && node2!=0;
			
			if(node1 != null && node2 == null){
				if(addNext == 0){
					pointer.next = node1;
				} else {
					pointer.next = addTwoNumbers(node1, ATU.new ListNode(addNext));
				} // end if else addNext==0;
			} // end if node != null
			else if (node2 != null && node1 == null){
				if(addNext == 0){
					pointer.next = node2;
				} else {
					pointer.next = addTwoNumbers(node2, ATU.new ListNode(addNext));
				}
			}  // end if node2 != null, node1==null;
			else if (node1 == null && node2 == null){
				if(addNext != 0){
					pointer.next = ATU.new ListNode(addNext);
				}
			} // end both node1 and node2 == null;
			
			return HeadNode;
			
		} // end addTwoNumbers class;
	} // end Solution class;
	

} // end all :)
