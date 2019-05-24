// Garrett DeAngelis, Hunter Harris
//CPE 349, Spring 2019
import java.util.Scanner;
import java.nio.file.*;
import java.io.IOException;

public class ChangeMaker
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);

      //get K-value
      //System.out.println("Please enter the following data in the correct order: ");
      System.out.println("Enter the number of coin-denominations and the set of coin values:");
      int k = scan.nextInt();
      //get the d-array
      int[] d = new int[k];
      for(int i = 0; i<k; i++)
      {
         d[i] = scan.nextInt();
      }

      //run the recurring program -> displays the results
      int n = 1;
      while(n > 0)
      {
         System.out.println("Enter a positive amount to be changed (enter 0 to quit):");
         n = scan.nextInt();
         if(n == 0)
         {
            System.out.println("Thanks for playing. Good Bye.");
            System.exit(0);
         }
         int[] result = new int[k];
         //result = change_DP(n, d);
         System.out.println("DP algorithm results");
         System.out.println("Amount: " + n);

         
         //System.out.println("Optimal distribution: " + );
         //System.out.println("Optimal coin count: " + );
      }
      //test the getDistribution function

      /*int[] testCoinCounts = new int[] {0, 0, 3, 1, 0, 2};
      int[] testD = new int[] {100, 50, 25, 10, 5, 1};
      String distribution = getDistribution(testCoinCounts, testD);
      System.out.println("test distribution: " + distribution);*/
      
   }
   // takes in the coinCounts output from DP and greedy algorithms, produces distribution string
   private static String getDistribution(int[] coinCounts, int[] d)
   {
      String distribution = "";
      // array d and coinCounts should have the same length
      //System.out.println("d.length: " + d.length);
      for(int i = 0; i<d.length-2; i++)
      {
         // we need to check to make sure that the coin is used, otherwise we don't add it to the distribution
         if(coinCounts[i] > 0)
            distribution = distribution + coinCounts[i] + "*" + d[i] + "c + ";
      }
      // get the last element --> this is because we need to end the "+" in the distribution string
      if(coinCounts[d.length-1] > 0)
         distribution = distribution + coinCounts[d.length-1] + "*" + d[d.length-1] + "c";

      return distribution;
   }
   public static int[] change_DP(int n, int[] d)
   {
      //placeholder function
      int[] result = new int[] {1, 2, 3, 4, 5};
      return result;
   }
   public static int[] change_greedy(int n, int[] d)
   {
      //placeholder function
      int[] result = new int[] {1, 2, 3, 4, 5};
      return result;
   }
   /*public static int[] change_DP(int n, int[] d)
   {
      int[] C = new int[n][n];
      for(int i = 0; i<n; i++)
      {
         for(int j = 0; j<n; j++)
         {
            break;
         }
      }
      
      
      return C[n];
   }
   public static int[] change_greedy(int n, int[] d)
   {
      int remaining = n;
      int highestCoin = 0;
      while(remaining > 0)
      {
         for(int i = 1; i<n; i++)
         {
            if(d[i] <= remaining)
            {
               highestCoin = i; 
               break;
            }
         }
         for(int i = highestCoin; i>0; i--)
         {
            remaining = highestcoin[i];
         }
      }
   }*/
}