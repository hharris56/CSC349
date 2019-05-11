// Hunter Harris, Garret DeAngelis
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;

public class GameProblem{

   // main method
   // reads in game board from input file + calls game method
   public static void main(String[] args){
      Scanner cmdline = new Scanner(System.in);
      System.out.print("Enter filename: ");
      String inputfile = cmdline.nextLine();
      Scanner file;
      try{
         Path filepath = Paths.get(inputfile);
         file = new Scanner(filepath);

         int[][] A = buildGame(file);
         game(A.length, A[0].length, A);
      }
      catch(IOException e){
         System.out.println("File not found");
      }
   }

   // takes input file (scanner)
   // returns constructed game matrix
   private static int[][] buildGame(Scanner file){
      int n = file.nextInt(), m = file.nextInt();
      int[][] A = new int[n][m];

      for (int i=0;i<n*m;i++){
         A[i/n][i%n] = file.nextInt();
      }
      return A;
   }

   // takes m*n matrix
   // calculates the maximum possible path on tbe matrix
   public static void game(int n, int m, int[][] A){
      int[][] S = new int[n][m];                         // sum matrix
      String[][] R = new String[n][m];                   // direction matrix (used to rebuild the path)

      S[n-1][m-1] = A[n-1][m-1];                         // fill in bot right index
      R[n-1][m-1] = "e";
      for (int i=n-2;i>=0;i--){                          // fill in right col
         if (S[i+1][m-1] > 0) {                          // move down
            S[i][m-1] = S[i+1][m-1] + A[i][m-1];         // sets new value within path value matrix
            R[i][m-1] = "d";                             // sets direction taken within direction matrix
         } else {                                        // exit, next value would reduce score
            S[i][m-1] = A[i][m-1];
            R[i][m-1] = "e";
         }
      }
      for (int j=m-2;j>=0;j--){                          // fill in bot row
         if (S[n-1][j+1] > 0) {                          // move right
            S[n-1][j] = S[n-1][j+1] + A[n-1][j];
            R[n-1][j] = "r";
         } else {                                        // exit
            S[n-1][j] = A[n-1][j];
            R[n-1][j] = "e";
         }
      }

      // fill in rest of table starting from furthest possible index and building upwards
      for (int i=n-2;i>=0;i--){
         for (int j=m-2;j>=0;j--) {
            if (S[i+1][j] > S[i][j+1]){                  // move down
               S[i][j] = A[i][j] + S[i+1][j];
               R[i][j] = "d";
            } else {                                     // move right
               S[i][j] = A[i][j] + S[i][j+1];
               R[i][j] = "r";
            }
         }
      }

//      printMatrix(A);
//      printMatrix(S);
//      printMatrix(R);
      int[] maxValue = findMax(S);                       // get max value
      printSoln(R, maxValue);                            // output solution
   }

   // prints out array in row/col format (INTEGER VALUES)
   private static void printMatrix(int[][] array){
      System.out.println();
      for (int i = 0;i<array.length;i++){                // interates through each row
         for (int j = 0;j<array[0].length;j++){          // prints all but last value of row
            System.out.print(array[i][j] + " ");
         }
         System.out.println();                           // prints last value of row
      }
   }

   // prints out array in row/col format (STRING VALUES)
   private static void printMatrix(String[][] array){
      System.out.println();
      for (int i = 0;i<array.length;i++){                // interates through each row
         for (int j = 0;j<array[0].length;j++){          // prints all but last value of row
            System.out.print(array[i][j] + " ");
         }
         System.out.println();                           // prints last value of row
      }
   }

   // returns array holding max value
   // and i/j values max was found at
   private static int[] findMax(int[][] S){
      int[] maxValue = new int[3];
      maxValue[0] = S[0][0];                             // default max to first value in array
      maxValue[1] = maxValue[2] = 0;                     // default indexes to [0,0]
      for (int i=0;i<S.length;i++){
         for (int j=0;j<S[0].length;j++){
            if (S[i][j] > maxValue[0]){
               maxValue[0] = S[i][j];
               maxValue[1] = i;
               maxValue[2] = j;
            }
         }
      }
      return maxValue;
   }

   // prints solution
   private static void printSoln(String[][] R, int[] maxValue){
      System.out.println("Best score: " + maxValue[0]);
      printPath(R, maxValue[1], maxValue[2]);
   }

   // recursively prints path taken to exit
   private static void printPath(String[][] R, int i, int j){
      if (R[i][j] == "e"){                                           // exit
         System.out.println("to ["+(i+1)+"]["+(j+1)+"] to exit");
      }
      else if (R[i][j] == "r"){                                      // move right
         System.out.print("["+(i+1)+"]["+(j+1)+"] to ");
         printPath(R, i, j+1);
      }
      else if (R[i][j] == "d"){                                      // move down
         System.out.print("["+(i+1)+"]["+(j+1)+"] to ");
         printPath(R, i+1, j);
      }
   }
}