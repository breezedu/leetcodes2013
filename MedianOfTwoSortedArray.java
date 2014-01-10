package leetCode;

public class MedianOfTwoSortedArray { 
	
	MedianOfTwoSortedArray Solutionss = new MedianOfTwoSortedArray();
	
	public static void main(String[] args){
		int[] arrayA = {1, 3, 4, 6, 7, 9, 24, 49};
		int[] arrayB = {4, 6, 8, 12, 23, 56, 68};
		double Median = Solution.findMedianSortedArrays(arrayA, arrayB);
		System.out.println("The median is: " + Median);
		
	}
	
	public static class Solution{
		public static double findMedianSortedArrays(int A[], int B[]){
			return 0;
			
		}
	}

}
