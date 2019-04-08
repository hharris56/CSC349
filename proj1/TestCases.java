import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestCases{

   int[] test1 = new int[]{11, 3, 9, 7, 34, 0, 2, 25, 19, 22};
   int[] test1_result = new int[]{0, 2, 3, 7, 9, 11, 19, 22, 25, 34};

   int[] test2 = new int[]{};
   int[] test2_result = new int[]{};

   int[] test3 = new int[]{30, -10, 20, 5, 20};
   int[] test3_result = new int[]{-10, 5, 20, 20, 30};

   public void testSeletion(){
      Sorts.selectionSort(test1, test1.length);
      System.out.println("test1 run");
      Sorts.selectionSort(test2, test2.length);
      System.out.println("test2 run");
      Sorts.selectionSort(test3, test3.length);
      System.out.println("test3 run");
   }

   public void testMerge(){
      int[] output = Sorts.mergeSort(test1, test1.length);
      assertEquals(output, test1_result);
      System.out.println("test1 run");
      Sorts.mergeSort(test2, test2.length);
      System.out.println("test2 run");
      Sorts.mergeSort(test3, test3.length);
      System.out.println("test3 run");
   }
}
