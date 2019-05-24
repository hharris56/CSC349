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
        int[] change = change_DP(n, d);
        printArray(change);
    }

    public static int[] change_DP(int n, int[] d){
        int[] C = new int[n];           // holds # of coins needed to change for each value
        int[] A = new int[n];           // holds index of selected coin from d array
        int[] B = new int[d.length];    // holds count for each coins value in d array
        C[0] = 0;                       // initial value, change for 0 is zero coins

        for (int j=0;j<n;j++){                                  // iterate through all values until given amount
            int min = 0;                                        // minimum value reference
            for (int i=0;i<d.length;i++){                       // iterate through coin values from d
                if (j > d[i] && (C[j - d[i]] + 1 < min)) {      // check if valid && check if less than min
                    min = C[j - d[i]] + 1;                      // update min reference
                    A[j] = i;                                   // hold index of coin used to acheive min
                }
            }
            B[A[j]]++;                                          // increment chosen coin
        }
    return B;
    }

    private static void printArray(int[] A){
        System.out.print("[");
        for (int i=0;i<A.length-1;i++){
            System.out.print(A[i] + ", ");
        }
        System.out.println(A[A.length-1] + "]");
    }
}