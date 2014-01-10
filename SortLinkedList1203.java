package leetCode;

import java.util.Scanner;

public class SortLinkedList1203 {
	static SortLinkedList1203 SLL = new SortLinkedList1203();
	
	private class ListNode{
		int val;
		ListNode next;
		
		ListNode(int x){
			val = getVal(x);
			next = null;
		}

		private int getVal(int x) {
			// TO return the value of val in the ListNode;
			return x;
		}
	} // end ListNode class;
	
	public static class Solution{
		
		public static ListNode sortList(ListNode head){
			
			System.out.println("sortList method!\n");
			
			if(head == null || head.next==null) {
				return head;
			}
			
			ListNode sortedHead = SLL.new ListNode(head.val);
			sortedHead.next = head.next;
			
			ListNode innerPointer = sortedHead;
			ListNode Pointer = innerPointer.next;
			while(innerPointer.next != null){
				
				while(Pointer!= null){
					if(Pointer.val < innerPointer.val){
						swapNodes(innerPointer, Pointer);	
					}
					Pointer = Pointer.next;
				} // end while Pointer!= null
				
				System.out.print(" " + innerPointer.val +" <=");
				innerPointer = innerPointer.next;
				Pointer = innerPointer.next;

			} // end while innerPointer!=null;
			
			
			return sortedHead;
			
		} // end sortList() method;

		private static void swapNodes(ListNode innerPointer, ListNode pointer) {
			// TO swap two nodes;
			int temp = pointer.val;
		 
			pointer.val = innerPointer.val;
			innerPointer.val = temp;
		}
		
	} // end Solution class;
	
	public static void main(String[] args){
		
		System.out.println("How many nodes do you need?");
		
		Scanner input = new Scanner(System.in);
		int NUM = input.nextInt();
		input.close();
		
		ListNode nodes = SLL.new ListNode((int) (Math.random()*100));
		ListNode pointer = nodes;
		
		for(int i=1; i<NUM; i++){
			pointer.next = SLL.new ListNode((int) (i + Math.random()*100));
			pointer = pointer.next;
			System.out.println("nodes[" + i +"]= " + pointer.val + " ");
		} // end for i<9; make nodes[i].next pointing to the nodes[i+1];
		
		//System.out.println("nodes[" + (NUM-1) +"]=" + pointer.val +" nodex["+ (NUM-1) +"].next=" + nodes[NUM-1].next);
		System.out.println("The original nodes are generated.");
		
		
		nodes = Solution.sortList(nodes);
		
		SLL.printLinkedList(nodes);
		
		System.out.println("The last step has finished. OK! ");
		
	} // end main();
	
	

	private void printLinkedList(ListNode LN) {
		// TO print out the whole linked list;
		
		System.out.println("\n\nPrint Linked List");
		
		if(LN!=null){
			System.out.print(" " + LN.val);
			//System.out.print(" " + LN.next);
			while(LN.next != null){
				
				LN = LN.next;
				System.out.print(" " + LN.val);
			} // end while;
			System.out.println("\n  END printLinkedList  ");
		} // end if;
		
		System.out.println();
		
	} // end printLinkedList() method;

} // end of the whole SLL class! :)
