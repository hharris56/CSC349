import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math.*;

public class Sorts{

   // selection sort, O(N)
   public static void selectionSort(int[] arr, int N){
      // i represents starting point of search
      for (int i=0;i<N;i++){
         int min_index = i;
         // iterate through all other values in list
         for (int j=i+1;j<N;j++){
            // check if value less than min
            if (arr[j] < arr[min_index]){
               min_index = j;
            }
         }
         // set min to beginning of search range
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

   // merge sort O(NlogN)
   // wrapper that calls mergeSort
   public static void mergeSort(int[] arr, int N){
      mergeSort(arr, 0, N-1);
   }

   // finds median pivot
   // sets pivot to last index of given list
   // also, to avoid worst case we rearrange 3 selected values to be in optimal order
   private static void setPivotToEnd(int[] arr, int first, int last){
      int median = (first + last) / 2;
      // set small of 2 values in leftmost index
      if (arr[first] > arr[median]){
         int temp = arr[first];
         arr[first] = arr[median];
         arr[median] = temp;
      }
      // set smaller of 2 values in leftmost in dex
      if (arr[first] < arr[last]){
         int temp = arr[first];
         arr[first] = arr[last];
         arr[last] = temp;
      }
      // set larger of 2 values in middle index
      if (arr[last] > arr[median]){
         int temp = arr[median];
         arr[median] = arr[last];
         arr[last] = temp;
      }
      // upon exit, smallest value in index 0, largest value at median,
      // middle value as pivot
   }

   // quickSort "functionality" where values move based off pivot
   // algorithm is as follows:
   // increment indexL until greater than pivot
   // decrement indexR until less than pivot
   // swap if values haven't "crossed over"
   // at end, swap pivot with indexL, return indexL as pivot index
   private static int splitList(int[] arr, int left, int right){
      int indexL = left, indexR = right-1, pivot = right;
      // check for crossover
      while (indexL <= indexR){
         // until indexL > pivot && keep index in bounds
         while ((arr[indexL] <= arr[pivot]) && (indexL < right)){
            indexL++;
         }
         // until crossover or indexR < pivot
         while ((indexL <= indexR) && (arr[indexR] >= arr[pivot])){
            indexR--;
         }
         // check to see if swap needed
         if (indexL <= indexR){
            // swap indexL + indexR
            int temp = arr[indexL];
            arr[indexL] = arr[indexR];
            arr[indexR] = temp;
            indexL = Math.max(left, indexL-1);
            indexR = Math.min(right-1, indexR+1);
         }
      }
      // swap pivot + indexL
      int temp = arr[indexL];
      arr[indexL] = arr[pivot];
      arr[pivot] = temp;
      return indexL;
   }

   // recursive quickSort function
   private static void quickSort(int[] arr, int first, int last) {
      if (first < last){
         setPivotToEnd(arr, first, last);
         int pivotIndex = splitList(arr, first, last);
         quickSort(arr, first, pivotIndex-1);
         quickSort(arr, pivotIndex+1, last);
      }
   }

   // quick sort O(NlogN)
   // wrapper that calls quickSort
   public static void quickSort(int[] arr, int N){
      quickSort(arr, 0, N-1);
   }
}
