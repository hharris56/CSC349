// Hunter Harris, Garret DeAngelis
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;

public class ChangeMaker{

    public static void main(String[] args){
        Scanner cmdline = new Scanner(System.in);
        System.out.print("Enter # of coins then their value in DECREASING order: ");
        int k = cmdline.nextInt();
        int[] d = new int[k];
        for (int i=0;i<k;i++){
            d[i] = cmdline.nextInt();
        }
        System.out.print("Enter change amount: ");
        int n = cmdline.nextInt();
        int[] change = change_DP(n+1, d);
        printArray(change);
    }

    public static int[] change_DP(int n, int[] d){
        int[] C = new int[n];           // holds # of coins needed to change for each value
        int[] A = new int[n];           // holds index of selected coin from d array
        int[] B = new int[d.length];    // holds count for each coins value in d array
        C[0] = 0;                       // initial value, change for 0 is zero coins

        for (int j=1;j<n;j++){                                  // iterate through all values until given amount
            int min = Integer.MAX_VALUE;                        // minimum value reference
            int index = 0;                                      // index of minimum value
            for (int i=0;i<d.length;i++){                       // iterate through coin values from d
                if (j >= d[i]) {                                // check if valid && check if less than min
                    if (C[j - d[i]] + 1 < min){
                        min = C[j - d[i]] + 1;                  // update min reference
                        index = i;                              // hold index of coin used to acheive min
                    }
                }
            }
            C[j] = min;
            A[j] = index;
        }
        buildResult(A, B, d);
    return B;
    }

    // method to build result list (B) from list of coin indexes (A)
    private static void buildResult(int[] A, int[] B, int[] d){
        int n = A.length-1;             // get last index
        while (n>0){
            int coinIndex = A[n];       // get index from index array
            B[coinIndex]++;             // increment related index
            n -= d[coinIndex];          // decrement n by coin value
        }
    }

    private static void printArray(int[] A){
        System.out.print("[");
        for (int i=0;i<A.length-1;i++){
            System.out.print(A[i] + ", ");
        }
        System.out.println(A[A.length-1] + "]");
    }
}