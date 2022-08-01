package horseracing;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        
        int []arr = new int[N];
        
       
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        int ans= 10000;
        int temp;
        
        for (int i = 0; i<N; i++) {
        	for (int j= i+1; j < N; j++) {
        		temp = Math.abs(arr[j] - arr[i]);
        		if (temp <ans) {
        			ans = temp;
        		}
        	}
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        in.close();
        System.out.println(ans);
    }
}