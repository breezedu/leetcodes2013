package leetCode2013;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*************************
 * G Ltd onsite question:
 * Every time call tripletPick() function, the function will pick 3 letters from a string length 6;
 * try to decode the original 6-letter string by calling the tripletPick() methods as many times as you like
 *  
 * @author Frog
 *
 */

public class DecodeTripletPassword {

	public static void main(String[] args){
		
		System.out.println("This is Decode Triplet Password program.");
		
		
		//1st, ask user to input a string of 6 letters
		System.out.println("Please input the original password:");
		Scanner input = new Scanner(System.in);
		System.out.print("Password: ");
		String oriPass = input.next();
		input.close();
		
		
		//2nd, create tripletPick() method to pick out 3 letters from original password, keep order;
		HashMap<String, Integer> tripSets = new HashMap<String, Integer>();
		ArrayList<String> setList = new ArrayList<String>();
		
		for(int i=0; i<3000; i++){
			String tripTemp = tripletPick(oriPass);
			
			if(!tripSets.containsKey(tripTemp)){
				setList.add(tripTemp);
				tripSets.put(tripTemp, 1);
				
			} else {
				
				tripSets.put(tripTemp, tripSets.get(tripTemp)+1);
			}
		}//end for i<3000 loop;
		
		System.out.println("The size of triplet set list is: " + setList.size());
		for(String tripStr:setList){
			System.out.println( tripStr + ", " +tripSets.get(tripStr));
		}
		
		
		//3rd, dfs decode the original password string base on the triplet set:
		//example: aaabbb 4, abcdef 20, aaaaaa 1, aaaaab 2, aaaaba 3, aaabaa 4, aabbaa 6, ababab 8, google 13;
		ArrayList<String> prosStrList = new ArrayList<String>();
		String head = "";
		dfsDecode(head, setList, prosStrList);
		
		
		//4th, printout the arrayList
		System.out.println("After dfsDecode, we got potential strings: ");
		printArrayList(prosStrList);
		
	}//end main();


	private static void dfsDecode(String head, ArrayList<String> setList, ArrayList<String> prosStrList) {
		// TODO Auto-generated method stub
		if(head.length()==6){
			
			//check if every triple-letter string in the setList could be found in the current head string;
			//if yes, add head to prosStrList arrayList;
			
			if(everyTriCouldbeFound(setList, head)){
				prosStrList.add(head);
				
			}			
			// prosStrList.add(head);
			return;
		}
		
		for(int i=0; i<setList.size(); i++){
			if(head==""){
				String headStr = setList.get(i);
				dfsDecode(headStr, setList, prosStrList);
			
			} else {				
				
				if(head.substring(head.length()-2).equals( setList.get(i).substring(0,2) )){
					System.out.println(head + ": " + head.substring(head.length()-2) +", next:" +setList.get(i));
					
					String headNext = head + setList.get(i).charAt(setList.get(i).length()-1);
					dfsDecode(headNext, setList, prosStrList);
				}
			}//end if-else head=="" conditions;
			
		}//end for i<setList.size() loop;
		
	}//end dfsDecode() method;

	private static boolean everyTriCouldbeFound(ArrayList<String> setList, String head) {
		// TODO Auto-generated method stub
		
		for(int index=0; index<setList.size(); index++){
			
			String currStr = setList.get(index);
			boolean found = false;
			for(int i=0; i<4; i++){
				for(int j=i+1; j<5; j++){
					for(int k=j+1; k<6; k++){
						
						if(currStr.charAt(0)==head.charAt(i) && currStr.charAt(1)==head.charAt(j) &&
							currStr.charAt(2)==head.charAt(k)) {
							found = true;
							i=6;j=6;k=6;
						}
					}
				}
			}
			
			if(found==false) return false;
		}
		return true;
	}


	private static String tripletPick(String oriStr) {
		// TODO pick out three letters from a stirng, keep the original order;
				
		int Len = oriStr.length();
		if(Len ==3) return oriStr;
		//here we know the length of the password string is 6;
		
		String retStr = "";
		
		int index1 = (int)(Math.random()*(Len-2));
		int index2 = (int)(Math.random()*(Len-2 - index1)) + index1+1;
		int index3 = (int)(Math.random()*(Len-1-index2)) + index2+1;
		
		//System.out.print(" indices: " + index1 + " " + index2 +" " + index3 +". ");
		
		return retStr+oriStr.charAt(index1) + oriStr.charAt(index2) + oriStr.charAt(index3);
	}//end tripletPick() method;
		

	private static void printArrayList(ArrayList<String> al) {
		// TODO printout every string in an arrayList
		if(al.size()==0){
			System.out.println("It's an empty arrayList.");
			return;
		}
		
		for(String s:al){
			System.out.println(" " +s);
		}
		
		System.out.println();
	}//end printArrayList() method;
	
	
}//end of everything in DecodeTripletPassword class;
