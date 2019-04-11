import java.util.concurrent.ThreadLocalRandom;

public class TestCases{

   private static int min = -10000;
   private static int max = 10000;

   // randomly generates an integer list of size N
   public static int[] makeUnsorted(int N){
      int[] arr = new int[N];
      for (int i = 0;i<N;i++){
         arr[i] = ThreadLocalRandom.current().nextInt(min, max+1);
      }
      return arr;
   }

   // checks that given array is in ascending order
   private static void checkSort(int[] arr, int N){
      for (int i = 0;i<N-1;i++) {
         if (arr[i] > arr[i + 1]) {
            System.out.println("wrong @ index " + i);
         }
      }
      //printArr(arr);
   }

   // prints specific range of array
   public static void printArr(int[] arr, int first, int last){
      System.out.print("[");
      for (int i = first;i<last;i++){
         System.out.print(arr[i] + ", ");
      }
      System.out.print(arr[last] + "]\n");
   }

   // prints entire array
   public static void printArr(int[] arr){
      if (arr.length == 0){
         return;
      }
      System.out.print("[");
      for (int i = 0;i<arr.length-1;i++){
         System.out.print(arr[i] + ", ");
      }
      System.out.print(arr[arr.length-1] + "]\n");
   }


   private static void testSelection(){
      int N = 0;
      int[] test1 = makeUnsorted(N);
      Sorts.selectionSort(test1, N);
      checkSort(test1, N);
      N = 100;
      int[] test2 = makeUnsorted(N);
      Sorts.selectionSort(test2, N);
      checkSort(test2, N);
      N = 10000;
      int[] test3 = makeUnsorted(N);
      Sorts.selectionSort(test3, N);
      checkSort(test3, N);
      N = 100000;
      int[] test4 = makeUnsorted(N);
      Sorts.selectionSort(test4, N);
      checkSort(test4, N);
   }

   private static void testMerge(){
      int N = 0;
      int[] test1 = makeUnsorted(N);
      Sorts.mergeSort(test1, N);
      checkSort(test1, N);
      N = 100;
      int[] test2 = makeUnsorted(N);
      Sorts.mergeSort(test2, N);
      checkSort(test2, N);
      N = 10000;
      int[] test3 = makeUnsorted(N);
      Sorts.mergeSort(test3, N);
      checkSort(test3, N);
      N = 1000000;
      int[] test4 = makeUnsorted(N);
      Sorts.mergeSort(test4, N);
      checkSort(test4, N);
   }

   private static void testQuick(){
      int N = 0;
      int[] test1 = makeUnsorted(N);
      Sorts.quickSort(test1, N);
      checkSort(test1, N);
      N = 100;
      int[] test2 = makeUnsorted(N);
      Sorts.quickSort(test2, N);
      checkSort(test2, N);
      N = 10000;
      int[] test3 = makeUnsorted(N);
      Sorts.quickSort(test3, N);
      checkSort(test3, N);
      N = 1000000;
      int[] test4 = makeUnsorted(N);
      Sorts.quickSort(test4, N);
      checkSort(test4, N);
   }

   public static void main(String[] args){
      System.out.print("Testing Selection... ");
      testSelection();
      System.out.println("Done");
      System.out.print("Testing Merge... ");
      testMerge();
      System.out.println("Done");
      System.out.print("Testing Quick... ");
      testQuick();
      System.out.println("Done");
   }

}
