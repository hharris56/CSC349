// Garrett DeAngelis, Hunter Harris
// CSC 349, Sring 2019

import java.util.Arrays;

public class Tester
{
    public static void main(String[] args)
    {
        int[] set1 = new int[] {100, 50, 25, 10, 5, 1}; // US denominations
        int[] set2 = new int[] {100, 50, 20, 15, 10, 5, 3, 2, 1 }; //Soviet Denominations
        int[] set3 = new int[] {64, 32, 16, 8, 4, 2, 1}; //Powers of 2
        int[] set4 = new int[] {100, 50, 25, 10, 1}; // US without nickel
        int[] set5 = new int[] {66, 35, 27, 18, 10, 1}; // random set

        System.out.println("Testing change_DP and change_greedy algorithms");

        // test set1 -----------------------------------------------------------
        int x1 = 0;
        for(int n = 1; n<201; n++)     // runs from 1 to 200 for the n-value
        {
            int dpResult = Arrays.stream(ChangeMaker.change_DP(n, set1)).sum();             // the stream.sum() function will compute the sum of all array elements,
            int greedyResult = Arrays.stream(ChangeMaker.change_greedy(n, set1)).sum();     // this will give us the optimal coin counts for the algorithms
            if(dpResult == greedyResult)                                                    // compare results + count matches
                x1++;
        }
        System.out.println("Testing set1: " + x1 + " matches in 200 tests ");   //display the number of matches

        // test set2 -----------------------------------------------------------
        int x2 = 0;
        for(int n = 1; n<201; n++)     // runs from 1 to 200 for the n-value
        {
            int dpResult = Arrays.stream(ChangeMaker.change_DP(n, set2)).sum();             // the stream.sum() function will compute the sum of all array elements,
            int greedyResult = Arrays.stream(ChangeMaker.change_greedy(n, set2)).sum();     // this will give us the optimal coin counts for the algorithms
            if(dpResult == greedyResult)                                                    // compare results + count matches
                x2++;
        }
        System.out.println("Testing set2: " + x2 + " matches in 200 tests ");

        // test set3 -----------------------------------------------------------
        int x3 = 0;
        for(int n = 1; n<201; n++)     // runs from 1 to 200 for the n-value
        {
            int dpResult = Arrays.stream(ChangeMaker.change_DP(n, set3)).sum();             // the stream.sum() function will compute the sum of all array elements,
            int greedyResult = Arrays.stream(ChangeMaker.change_greedy(n, set3)).sum();     // this will give us the optimal coin counts for the algorithms
            if(dpResult == greedyResult)                                                    // compare results + count matches
                x3++;
        }
        System.out.println("Testing set3: " + x3 + " matches in 200 tests ");

        //test set4 -----------------------------------------------------------
        int x4 = 0;
        for(int n = 1; n<201; n++)     // runs from 1 to 200 for the n-value
        {
            int dpResult = Arrays.stream(ChangeMaker.change_DP(n, set4)).sum();             // the stream.sum() function will compute the sum of all array elements,
            int greedyResult = Arrays.stream(ChangeMaker.change_greedy(n, set4)).sum();     // this will give us the optimal coin counts for the algorithms
            if(dpResult == greedyResult)                                                    // compare results + count matches
                x4++;
        }
        System.out.println("Testing set4: " + x4 + " matches in 200 tests ");

        //test set5 -----------------------------------------------------------
        int x5 = 0;
        for(int n = 1; n<201; n++)     // runs from 1 to 200 for the n-value
        {
            int dpResult = Arrays.stream(ChangeMaker.change_DP(n, set5)).sum();             // the stream.sum() function will compute the sum of all array elements,
            int greedyResult = Arrays.stream(ChangeMaker.change_greedy(n, set5)).sum();     // this will give us the optimal coin counts for the algorithms
            if(dpResult == greedyResult)                                                    // compare results + count matches
                x5++;
        }
        System.out.println("Testing set5: " + x5 + " matches in 200 tests ");
    }
}