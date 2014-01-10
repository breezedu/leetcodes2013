package leetCode;

import java.util.Scanner;

public class SortLinkedList1202 {
	static SortLinkedList1202 SLL = new SortLinkedList1202();
	
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
		
		ListNode[] nodes = new ListNode[NUM];
		for(int i=0; i<NUM; i++){
			nodes[i] = SLL.new ListNode((int) (i + Math.random()*100));
			
		} // end for i<10 loop; assign a random value to each nodes[i].val;
		
		for(int i=0; i<NUM-1; i++){
			nodes[i].next = nodes[i+1];
			System.out.println("nodes[" + i +"]= " + nodes[i].val +" nodes["+i+"].next.val=" + nodes[i].next.val+ " ");
		} // end for i<9; make nodes[i].next pointing to the nodes[i+1];
		
		System.out.println("nodes[" + (NUM-1) +"]=" + nodes[NUM-1].val +" nodex["+ (NUM-1) +"].next=" + nodes[NUM-1].next);
		System.out.println("The original nodes are generated.");
		
		
		nodes[0] = Solution.sortList(nodes[0]);
		
		SLL.printLinkedList(nodes[0]);
		
		System.out.println("\nThe last step has finished. OK! ");
		
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
