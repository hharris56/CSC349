// Hunter Harris (hvharris), Garrett DeAngelis (gsdeange)
// CSC 349 - Spring 2019

// checks SortTimes for the Sorts class
import java.util.Random;

public class SortTimes
{

   private static final int MAX_SIZE = 160000;

   public static void main(String[] args)
   {
      
      long startTime, endTime;
      long time1, time2, time3;

      int[] elements1, elements2, elements3;

      elements1 = new int[MAX_SIZE];
      elements2 = new int[MAX_SIZE];
      elements3 = new int[MAX_SIZE];


      //Run through the times, starting at N = 5,000, going to 160,000
      System.out.println("Running Times of three sorting algorithms");
      System.out.println();
      for(long N = 5000; N<=MAX_SIZE; N = N*2)
      {
         //System.out.println("N-value: " + N);
         for(int i = 0; i<5; i++)
         {
            //System.out.println();
            generateLists(elements1, elements2, elements3, N);
            //System.out.println("elements1: " + elements1[0] + " elements2: " + elements2[0] + " elements3: " + elements3[0]);

            //check Selection sort times
            startTime = System.nanoTime();
            Sorts.selectionSort(elements1, (int)N);
            endTime = System.nanoTime();
            time1 = (endTime-startTime)/1000000;

            //check MergeSort times
            startTime = System.nanoTime();
            Sorts.mergeSort(elements2, (int)N);
            endTime = System.nanoTime();
            time2 = (endTime-startTime)/1000000;

            //check QuickSort times
            startTime = System.nanoTime();
            Sorts.quickSort(elements3, (int)N);
            endTime = System.nanoTime();
            time3 = (endTime-startTime)/1000000;

            //output the times
            System.out.println("N=" + N + ": T_ss=" + time1 + ", T_ms=" + time2 + ", T_qs="+ time3);

         }
      }
      System.out.println();
      System.out.println("End of output");

   }
   private static void generateLists(int[] elements1, int[] elements2, int[] elements3, long size)
   {
      //generates the random lists for the methods above
      Random rand = new Random();
      for(int i = 0; i<size; i++)
      {
         int random = rand.nextInt((MAX_SIZE)-1);
         elements1[i] = random;
         elements2[i] = random;
         elements3[i] = random;
      }
   }
}
