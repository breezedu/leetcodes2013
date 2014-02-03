package leetCode;

import java.util.Scanner;

/***********
 * There are N gas stations along a circular route, 
 * where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] 
 * of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, 
 * otherwise return -1.
 * 
 * Note: 
 * The solution is guaranteed to be unique.
 * 
 * @author Frog :)
 *
 */

public class GasStation {

	public static void main(String[] args){
		
		/*******
		 * 1st, build the cycle of gas station;
		 * array Gas[i] store all the gas volum stored here;
		 * array Cost[i] store all the gas needed from Gas[i] to Gas[i+1]; 
		 * the last Cost[End] is the gas needed from Gas[End] to Gas[0];
		 */
		
		System.out.println("This is the gas-station program.");
		System.out.println("Please input the # of gas stations on the routine:");
		
		Scanner input = new Scanner(System.in);
		
		int NumOfGas = input.nextInt();
		input.close();
		
		int[] Gas = new int[NumOfGas];
		int[] Cost = new int[NumOfGas];
		
		for(int i=0; i<NumOfGas; i++){
			
			Gas[i] = (int) (Math.random() * 10);
			Cost[i] = (int) (Math.random() * 10);
		} // end of for i<NumOfGas loop; all cost and gas array were assigned;
		
		System.out.println("The gas volume at each gas statin is: ");
		printArray(Gas);
		System.out.println("The gas needed to reach next station is: ");
		printArray(Cost);
		
		int Index = canCompleteCircuit(Gas, Cost);
		
		if(Index == -1){
			System.out.println("The cycle could NOT be completed.");
		} else {
			System.out.println("The cycle could start from "+Index +". End :)");
		}
		
	} // end of main();

	private static void printArray(int[] intArray) {
		// TODO Printout each element in the array
		int Len = intArray.length;
		int sum = 0;
		for(int i=0; i<Len; i++){
			System.out.print(" " + intArray[i]);
			sum += intArray[i];
		}
		System.out.println("   total = " + sum);
		
	} // end printArray() method;

	private static int canCompleteCircuit(int[] gas, int[] cost) {
		// TODO To check if the cycle routine could be walked through
		int sum =0;
		int total =0;
		int index =0;
		int Len = gas.length;
		
		for(int i=0; i<Len; i++){
			
			sum += gas[i] - cost[i]; // the total gas amount left after traveling from gas[0] to gas[i];
									// if at any gas station, we get a sum <0; which mean the car can not move
									// in this way we have to reset the starting point to be gas[i+1];
			total += gas[i] - cost[i]; // the total gas collected; after travelled the whole cycle, check the total gas left;
										// if the total gas amount left <0, that means the car can not travel around the whole cycle;
			
			/**********
			 * if sum < 0. the car can not move from gas[i] to gas[i+1], so we have to reset 
			 * the starting point to gas[i+1]; then pass the i+1 to the index;
			 */
			if(sum<0){
				sum = 0;
				index = i+1;
				
			} // end if sum<0 condition;
			
		} // end for i<Len loop; traversal all gas stations on the cycle;
		
		if(total>=0){
			return index;
			
		} else {
			
			return -1;
		} // end if-else condition; return the index.

	} // end of canCompleteCircuit() method;
	
} // end of everything in GasStation class;
