package leetCode;

import java.util.Scanner;

class ListNode{
	int val;
	ListNode next;
	
	ListNode(int x){
		val = x;
		next = null;
	}
	
} // end of ListNode class;

public class LinkedListCycle {
	
	public static void main(String[] args){
		/**************
		 * first section, build a linked list;
		 * ask user to input #N of LinkedNodes; (N<20);
		 * then build N nodes;
		 * the last node's next pointer point to a random value (1, 100);
		 * if the random value <N, we point the last node to that #value of node
		 * if the random value >N, there's no cycle in the linked list;
		 */
		
		System.out.println("Please input how many nodes do you like in the linked list?");
		Scanner input = new Scanner(System.in);
		int Length = input.nextInt();
		input.close();
		
		ListNode Head = new ListNode((int) (Math.random()*100));
		ListNode point = Head;
		for(int i=1; i<Length; i++){
			point.next = new ListNode((int) (Math.random()*100));
			point = point.next;
		} // end for i<Length-1 loop; the val of each Node has been assigned;
		
		int Pivot = (int) (Math.random()*Length*2); // the is the "node" the last node should points to;
		System.out.println("The random integrate is: " + Pivot);
		
		if(Pivot < Length-1){
			// the pointer of the last Node will point to the Node #Pivot
			ListNode p = Head;
			ListNode q = Head;
			while(p.next!=null){
				p = p.next;
			} // end while, till the end of the original linked list;
			
			for(int i=0; i<Pivot; i++){
				q = q.next;
			} // end for i<Pivot loop; q pointing to the Pivot-th node now;
			p.next =q;
			
		} // end if-else loop;
		
		/**********
		 * print out the liked list now;
		 * it depends on the integrate generated randomly; 
		 */
		printNodes(Head, Length);
		
		boolean cycle = checkCycle(Head);
		if(cycle){
			System.out.println("Yes, there's at lest one cycle.");
		} else {
			System.out.println("There's no cycle.");
		}
		
	} // end main();

	private static boolean checkCycle(ListNode Head) {
		// TO check whether there's a cycle in the original LinkedList or not;
		ListNode Fast = Head;
		if (Fast == null || Fast.next == null)
			return false;
		
		ListNode Slow = Head;
		while(Fast != null && Fast.next !=null){
			Slow = Slow.next;
			Fast=Fast.next.next;
			if(Fast == Slow)
				return true;
		} // end while loop;
		
		return false;
	} // end checkCycle() mehtod;

	private static void printNodes(ListNode Head, int Length) {
		// TO print the whole Linked List;
		ListNode printNode = Head;
		for(int i=0; i<Length; i++){
			
			System.out.print(" "+ printNode.val +"");
			if(printNode.next != null){
				System.out.print(" --> ("+printNode.next.val+")");
				printNode = printNode.next;
			
			} else {
				System.out.print(" End.");
			}
			
		} // end for i<Length loop; finish printing nodes;
		System.out.println();
	} // end of printNodes() method;

} // end of everything;
