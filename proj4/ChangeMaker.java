// Hunter Harris, Garret DeAngelis
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;

public class ChangeMaker{

    public static void main(String[] args) {
        Scanner cmdline = new Scanner(System.in);
        System.out.print("Enter the number of coin-denominations and the set of coin values: ");
        int k = cmdline.nextInt();
        int[] d = new int[k];
        for (int i=0;i<k;i++){
            d[i] = cmdline.nextInt();
        }
        while (true) {
            System.out.print("Enter a positive amount to be changed (enter 0 to quit): ");
            int n = cmdline.nextInt();
            if (n == 0) {
                break;
            }
            int[] dp_result = change_DP(n, d);
            displayResult("DP", n, d, dp_result);
            int[] greedy_result = change_greedy(n, d);
            displayResult("Greedy", n, d, greedy_result);
        }
        System.out.println("Thanks for playing. Good Bye.");
    }

    // bottom up calculation of change maker
    public static int[] change_DP(int n, int[] d){
        int[] C = new int[n+1];         // holds # of coins needed to change for each value
        int[] A = new int[n+1];         // holds index of selected coin from d array
        int[] B = new int[d.length];    // holds count for each coins value in d array
        C[0] = 0;                       // initial value, change for 0 is zero coins

        for (int j=1;j<=n;j++){                                 // iterate through all values until given amount
            int min = Integer.MAX_VALUE;                        // minimum value reference
            int index = 0;                                      // index of minimum value
            for (int i=0;i<d.length;i++){                       // iterate through coin values from d
                if (j >= d[i]) {                                // check if valid && check if less than min
                    if (C[j - d[i]] + 1 < min) {
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

    // greedy calculation of change maker
    public static int[] change_greedy(int n, int[] d){
        int B[] = new int[d.length];
        int i = 0;
        while (n > 0) {                                         // while change amount > 0
            int count = howMany(n, d[i]);                       // how many of coin fit
            B[i] += count;                                      // inc coin index
            n -= count*d[i];                                    // update change amount
            i++;                                                // next coin value
        }
        return B;
    }

    // calculates how many of B fits in A (no remainder)
    private static int howMany(int A, int B){
        int i = 1;
        while (A >= B*i){
            i++;
        }
        return i-1;         // decrememnt bc curr i amount breaks truth statement
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

    public static void printArray(int[] A){
        System.out.print("[");
        for (int i=0;i<A.length-1;i++){
            System.out.print(A[i] + ", ");
        }
        System.out.println(A[A.length-1] + "]");
    }

    // displays results of change maker
    public static void displayResult(String name, int amount, int[] d, int[] B){
        System.out.println(name + " algorithm results");
        System.out.println("Amount: " + amount);
        System.out.print("Optimal distribution: ");
        printCoins(B, d);
        int total=0;
        for (int i=0;i<B.length;i++){       // get total coin count
            total += B[i];
        }
        System.out.println("Optimal coin count: " + total);
        System.out.println();
    }

    // displays coin value in proper format
    private static void printCoins(int[] B, int[] d){
        boolean first = true;               // used to check when to print "+"
        for (int i=0;i<B.length;i++){
            if (B[i] != 0){
                if (first == false){
                    System.out.print("+ ");
                }
                first = false;
                System.out.print(B[i]+"*"+d[i]+"c ");
            }
        }
        System.out.println();
    }

}