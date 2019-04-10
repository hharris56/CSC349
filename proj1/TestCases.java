import java.util.concurrent.ThreadLocalRandom;

public class TestCases{

   private static int min = -10000;
   private static int max = 10000;

   // randomly generates an integer list of size N
   private static int[] makeUnsorted(int N){
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


   private static void checkSelection(){
      int[] test1 = makeUnsorted(10);
      Sorts.selectionSort(test1, 10);
      checkSort(test1, 10);
      int[] test2 = makeUnsorted(0);
      Sorts.selectionSort(test2, 0);
      checkSort(test2, 0);
      int[] test3 = makeUnsorted(1000);
      Sorts.selectionSort(test3, 1000);
      checkSort(test3, 1000);
      int[] test4 = makeUnsorted(10000);
      Sorts.selectionSort(test4, 10000);
      checkSort(test4, 10000);
   }

   private static void checkMerge(){
      int[] test1 = makeUnsorted(10);
      Sorts.mergeSort(test1, 10);
      checkSort(test1, 10);
      int[] test2 = makeUnsorted(0);
      Sorts.mergeSort(test2, 0);
      checkSort(test2, 0);
      int[] test3 = makeUnsorted(10);
      Sorts.mergeSort(test3, 10);
      checkSort(test3, 10);
      int[] test4 = makeUnsorted(10000);
      Sorts.mergeSort(test4, 10000);
      checkSort(test4, 10000);
   }

   private static void checkQuick(){
      int[] test1 = makeUnsorted(10);
      Sorts.quickSort(test1, 10);
      checkSort(test1, 10);
      int[] test2 = makeUnsorted(0);
      Sorts.quickSort(test2, 0);
      checkSort(test2, 0);
      int[] test3 = makeUnsorted(10000);
      Sorts.quickSort(test3, 10000);
      checkSort(test2, 10000);
   }

   public static void main(String[] args){
      //checkSelection();
      //checkMerge();
      checkQuick();
   }

}
