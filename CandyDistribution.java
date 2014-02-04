package leetCode;

import java.util.Scanner;

/*********************
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * 
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * 
 * @author Fog
 *
 */

public class CandyDistribution {
	
	public static void main(String[] args){
		
		System.out.println("This is a candy distribution problem.");
		System.out.println("Please input how many children are there in the line:");
		
		Scanner input = new Scanner(System.in);
		
		int Num = input.nextInt();
		input.close();
		
		int[] children = new int[Num];
		
		for(int i=0; i<Num; i++){
			
			children[i] = (int)(Math.random()*10);
		} // end for i<Num loop; the rating value of each child has been assigned;
		
		int sum = candy(children);
		
		System.out.println("\nWe need " + sum +" candies.");
	} // end of main();

	private static int candy(int[] ratings) {
		// TODO To calculate the total candies needed.
		/*******
		 * 1st, build an array of candy[], each element means # of candies for each child;
		 * iterate from left to right, all child with a rating > than his left neighbor will have 1 more candy;
		 * But, rating array like 2 4 5 4 3 1, each child will get 1 2 3 1 1 1.
		 * the last three children will get the same amount of candies;
		 * which is not correct; so we need to do the iteration again from right to left;
		 * if rating[i-1] > rating[i] && candy[i-1]<=candy[i], give child[i-1] more candies than child[i];
		 * End
		 */
		
		int Len = ratings.length;
		
		if(Len == 0){
			return 0;
		}
		
		int[] candy = new int[Len];
		for(int i=0; i<Len; i++){
			candy[i] = 1;
			System.out.print(" " + ratings[i]);
		}
		
		System.out.println();
		
		for(int i=1; i<Len; i++){
			
			if(ratings[i] > ratings[i-1]){
				candy[i] = candy[i-1] + 1;
				
			}

		} // end checking min rating loop; 
		System.out.println();
		
		int sum = candy[Len-1];
		
		for(int i=Len-2; i>=0; i--){
			
			if(ratings[i] > ratings[i+1]){
				candy[i] = candy[i+1]+1;
			}
			
			sum += candy[i];
			
		} // end for i<Len loop; all children standing on the right side get their candies;
				
		for(int i=0; i<Len; i++){
			System.out.print(" " + candy[i]);
		}
		
		return sum;
	}

} // end of everything in CandyDistribution class;
