package leetCode;

import java.util.Stack;

/**********
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. 
 * Each operand may be an integer or another expression.
 * 
 * Some examples:
 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * @author Frog
 *
 */

public class EvaluateReversePolishNotation {
	
	public static void main(String[] args){
		
		String[] tokens = {"4", "13", "5", "/", "+"};  
		String[] token2 = {"2", "1", "+", "3", "*" };
		
		int result = evalPRN(tokens);
		int result2 = evalPRN(token2);
		System.out.println("The result of tokens sequence is: " + result +" and " + result2);
		
	} // end of main();
	

	private static int evalPRN(String[] tokens) {
		// TODO Auto-generated method stub
		int Len = tokens.length; // the length of tokens string array;
		Stack<String> tokenStack = new Stack<String>();
		
		for(int i=0; i<Len; i++){
			
			if(tokens[i].equals("+")){
				int val1 = Integer.parseInt(tokenStack.pop());
				int val2 = Integer.parseInt(tokenStack.pop());
				int val = val1 + val2;
				tokenStack.push(String.valueOf(val));
				
			} else if(tokens[i].equals("-")){
				int val1 = Integer.parseInt(tokenStack.pop());
				int val2 = Integer.parseInt(tokenStack.pop());
				int val = val1 - val2;
				tokenStack.push(String.valueOf(val));
				
			} else if(tokens[i].equals("*")){
				
				int val1 = Integer.parseInt(tokenStack.pop());
				int val2 = Integer.parseInt(tokenStack.pop());
				int val = val1 * val2;
				tokenStack.push(String.valueOf(val));
			} else if(tokens[i].equals("/")){
				
				int val1 = Integer.parseInt(tokenStack.pop());
				int val2 = Integer.parseInt(tokenStack.pop());
				int val = val1 / val2;
				tokenStack.push(String.valueOf(val));
			} else {

				tokenStack.push(tokens[i]);
				
			} // end if-else conditions;
			
		} // end for i<Len loop;
		
		int fVal = Integer.parseInt( tokenStack.pop() );
		
		return fVal;
	} // end evalPRN() method.
	
	

} // end of everything in EvaluateReversePolishNotation.
