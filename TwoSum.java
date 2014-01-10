package leetCode;

import java.util.HashMap;


public class TwoSum {
	
	public static void main(String[] args){
		int sum = 9;
		int[] nums = {2, 7, 11, 15};
		int[] index = new int[2];
		
		index = twoSum(nums, sum);
		
		 System.out.println("index1=" +index[0] +" index2=" + index[1]);
	}
	
	
    public static int[] twoSum(int[] numbers, int target) {
        
        // DO NOT write main() function, fxxking stupid requirement, I have to reconstruct the main();
    	
        HashMap<Integer, Integer> Hashmap = new HashMap<Integer, Integer>();  
        int Len = numbers.length;  
        int[] result = new int[2]; 
        
        for (int i = 0; i < Len; i++){  
        	// take numbers[i] as the key, i as the value;
            if (Hashmap.containsKey(target - numbers[i])){
            	
                result[0] = Hashmap.get(target-numbers[i]) + 1;  
                result[1] = i + 1;  
                break;  
            } else {  
                Hashmap.put(numbers[i], i);  
            } // end if-else;  
            
        } // end for i<Len; 
        
        return result;  
        } // end twoSum method;
 
}
