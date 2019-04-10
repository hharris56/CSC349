import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math.*;

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

   // sorts a given array (arr) using the index of 2 subarrays
   // arr1 = first to middle INCLUSIVE; arr2 = middle+1 to last INCLUSIVE
   private static void mergeSortedHalves(int[] arr, int first, int middle, int last){
      int[] temp = new int[last - first + 1];
      int index1 = first, index2 = middle+1, i = 0;

      // compare until one of the two sublists is empty
      while ((index1 <= middle) && (index2 <= last)){
         // add lower value
         if (arr[index1] < arr[index2]){
            temp[i] = arr[index1];
            index1++;
         }
         else{
            temp[i] = arr[index2];
            index2++;
         }
         i++;
      }
      // add until arr1 is empty
      while (index1 <= middle){
         temp[i] = arr[index1];
         index1++;
         i++;
      }
      // add until arr2 is empty
      while (index2 <= last){
         temp[i] = arr[index2];
         index2++;
         i++;
      }
      // copy values from temp onto arr
      for (i=0;i<=last-first;i++){
         arr[first+i] = temp[i];
      }
   }

   // recursive mergeSort call, splits until length < 2 and envokes method to sort halves
   private static void mergeSort(int[] arr, int first, int last){
      // base case catch
      if (first < last){
         int middle = (first + last) / 2;
         mergeSort(arr, first, middle);
         mergeSort(arr, middle+1, last);
         mergeSortedHalves(arr, first, middle, last);
      }
   }

   // wrapper that calls mergeSort
   public static void mergeSort(int[] arr, int N){
      mergeSort(arr, 0, N-1);
   }

   private static void setPivotToEnd(int[] arr, int first, int last){
      int median = (first + last) / 2;
      if (arr[first] > arr[median]){
         int temp = arr[first];
         arr[first] = arr[median];
         arr[median] = temp;
      }
      if (arr[first] < arr[last]){
         int temp = arr[first];
         arr[first] = arr[last];
         arr[last] = temp;
      }
      if (arr[last] > arr[median]){
         int temp = arr[median];
         arr[median] = arr[last];
         arr[last] = temp;
      }
   }

   private static int splitList(int[] arr, int left, int right){
      int indexL = left, indexR = right-1, pivot = right;
      while (indexL < indexR){
         while ((arr[indexL] < arr[pivot]) && (indexL < right)){
            indexL++;
         }
         while ((indexL <= indexR) && (arr[indexR] > arr[pivot])){
            indexR--;
         }
         if (indexL < indexR){
            int temp = arr[indexL];
            arr[indexL] = arr[indexR];
            arr[indexR] = temp;
            indexL = Math.max(left, indexL-1);
            indexR = Math.min(right, indexR+1);
         }
      }
      int temp = arr[indexL];
      arr[indexL] = arr[pivot];
      arr[pivot] = temp;
      return indexL;
   }


   private static void quickSort(int[] arr, int first, int last) {
      if (first < last){
         setPivotToEnd(arr, first, last);
         int pivotIndex = splitList(arr, first, last);
         quickSort(arr, first, pivotIndex-1);
         quickSort(arr, pivotIndex+1, last);
      }
   }

   public static void quickSort(int[] arr, int N){
      quickSort(arr, 0, N-1);
   }
}
