package horseracing;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution2 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        
        int []arr = new int[N];
        
       
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        
        int ans= 10000;
        int temp;
        Arrays.sort(arr);
        for (int i = 0; i<N-1; i++) {
        	temp = Math.abs(arr[i] - arr[i+1]);
        		if (temp <ans) {
        			ans = temp;
        		}
        	
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        in.close();
        System.out.println(ans);
    }
}