import java.util.Arrays;

public class Sorts{

   public static void selectionSort(int[] arr, int N){
      for (int i=0;i<N;i++){
         int min_index = i;
         for (int j=i+1;j<N;j++){
            if (arr[j] < arr[min_index]){
               min_index = j;
            }
         }
         int temp = arr[i];
         arr[i] = arr[min_index];
         arr[min_index] = temp;
      }
   }
   
   // mergesort runner, made to be called recurrsively
   private static int[] split(int[] arr, int N){
      if (N == 1){                                                // base case
         return arr;
      }
      int[] arr1 = split(Arrays.copyOfRange(arr, 0, N/2), N/2);   // split into 2 halfs
      int[] arr2 = split(Arrays.copyOfRange(arr, N/2, N), N-N/2);
      
      return merge(arr1, arr2);
   }
   
   // rebuilds list in order after being divided
   private static int[] merge(int[] arr1, int[] arr2){
      int[] combined = new int[arr1.length + arr2.length];     // initialize result array, size of params combined
      int i = 0;                                               // index to place next value at
      while (!(arr1 == null) && !(arr2 == null)){              // check until one of the lists is empty
         if (arr1[0] > arr2[0]){
            combined[i] = arr1[0];
            arr1 = Arrays.copyOfRange(arr1, 1, arr1.length);
         }
         else{
            combined[i] = arr2[0];
            arr2 = Arrays.copyOfRange(arr2, 1, arr2.length);
         }
         i++;
      }

      return arr1;
   }

   public static int[] mergeSort(int[] arr, int N){
      return split(arr, N);
   }

   public static void quickSort(int[] arr, int N){
   }
}
