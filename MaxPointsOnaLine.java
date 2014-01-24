package leetCode;

import java.util.HashMap;
import java.util.Scanner;

/***********
 * Given n points on a 2D plane, 
 * find the maximum number of points that lie on the same straight line.
 */

class Point{
	int x;
	int y;
	
	Point(){
		x=0;
		y=0;
		
	} // end point(0,0);
	
	Point(int a, int b){
		x= a;
		y =b;
	} // end point(a,b);
	
} // end Point class;


public class MaxPointsOnaLine {

	public static void main(String[] args){
		/**********
		 * 1st, generate an array[] of points;
		 * here we just randomly generate points(x,y) within [0,1000];
		 * ask the user to input how many points?
		 */
		System.out.println("Please input how many points you want to generate?");
		Scanner input = new Scanner(System.in);
		int numPoints = input.nextInt();
		input.close();
		
		Point[] randomPoints = new Point[numPoints];
		
		for(int i=0; i<numPoints; i++){
			int a = (int)(Math.random()*1000);
			int b = (int)(Math.random()*1000);
			Point tempP = new Point(a, b);
			
			randomPoints[i] = tempP;
			
		} // end for i<numPoints loop; all points created;
		
		int maxP = maxPoints(randomPoints);
		
		System.out.println("There are " + maxP + " points in the max line;");
		
	} // end of main();
	
	/******************
	 * Unless the two points are the same, we could draw a line between two points;
	 * Take the k_value of two point as the "key", k = (y2-y1)/(x2-x1); 
	 * Take the number of points on that line the value;
	 * Put the key and value pair into a HashTable;
	 * After iterated all points in the Points[] array, check the hashTable;
	 * If any value on the HashTable > max; set the value as the max, 
	 * then calculate next iteration; 
	 * @param points
	 * @return
	 */
	private static int maxPoints(Point[] points) {
		// TODO To calculate the maxPoints in a line;
		
		if (points==null||points.length==0){
            return 0;
        }  // end if there's no point condition;
        
        HashMap<Double, Integer> mapPointsnLine=new HashMap<Double, Integer>();;
        int max=1; // at least there's one point on a line; 
        
        for(int i=0; i<points.length; i++){
            //for every new point, clear the mapPointsnLine map;
        	mapPointsnLine.clear();
            
            /**************
             * if point[j].x == point[i].x && point[j].y == point[i].y;
             * they are the same point; the k_value could be 
             * represented by Integer.MIN_VALUE
             */
        	mapPointsnLine.put((double)Integer.MIN_VALUE, 1);
            
            int dupliPoints=0; // this is the duplicated points in the set;
            
            for(int j=i+1; j<points.length; j++){
                
               if (points[j].x==points[i].x && points[j].y==points[i].y){
                   dupliPoints++;
         
               } // end if p[i] = p[j] condition;
               
               /********
                * technically, there are two situations for k_value
                * 1: point[j].x == point[i].x, the line is vertical to x-axis;
                * 	under this condition, k_value is Max_value;
                * 2: others, we can get normal k_value by (y2-y1)/(x2-x1);
                *  
                */
               double key=points[j].x-points[i].x==0?Integer.MAX_VALUE:0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x);
              
               if (mapPointsnLine.containsKey(key)){
            	   
            	   mapPointsnLine.put(key, mapPointsnLine.get(key)+1);
            	   
               } else{
            	   
            	   mapPointsnLine.put(key, 2);
            	   
               } // end if-else condition;
               
           } // end for j<points.length loop; all points after point[i] have been checked;
            
           
          for (int key: mapPointsnLine.values()){
            
              // duplicate may exist
              if (key + dupliPoints > max){
                  max = key + dupliPoints;
              }
              
          } // end for temp:mapPointsnline loop
           
        } // end for i< points.length loop;
        
        return max;
        
    } // end maxPoints() method; 
		
	
} // end of everything.