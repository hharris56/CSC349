// Hunter Harris
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;

public class GameProblem{

   // main method
   // reads in game board + calls game method
   public static void main(String[] args){
      Scanner cmdline = new Scanner(System.in);
      System.out.print("Enter filename: ");
      String inputfile = cmdline.nextLine();
      Scanner file;
      try{
         Path filepath = Paths.get(inputfile);
         file = new Scanner(filepath);
      }
      catch(IOException e){
         System.out.println("File not found");
      }
   }

   private static int[][] buildGame(Scanner file){
      int n = file.nextInt(), m = file.nextInt();
      int[][] game = new int[n][m];

      for (int i=0;i<n*m;i++){
         game[i/n][i%n] = file.nextInt();
      }
      return game;
   }

   // takes m*n matrix
   // calculates the maximum possible path on tbe matrix
   public static void game(int m, int n, int[][] A){
      int[][] S = new int[m][n];                         // sum matrix
      String[][] D = new String[m][n];                   // direction matrix (used to rebuild the path)

      S[m-1][n-1] = A[m-1][n-1];                         // fill in bot right index
      for (int i=m-2;i<=0;i--){                          // fill in right col
         if (S[i+1][n-1] > 0) {
            S[i][n-1] = S[i+1][n-1] + A[i][n-1];
            D[i][n-1] = "d";
         } else{
            S[i][n-1] = A[i][n-1];
            D[i][n-1] = "e";
         }
      }
      for (int j=n-2;j<=0;j--){                          // fill in bot row
         if (S[m-1][j+1] > 0) {
            S[m-1][j] = S[m-1][j+1] + A[m-1][j];
            D[m-1][j] = "r";
         } else {
            S[m-1][j] = A[m-1][j];
            D[m-1][j] = "e";
         }
      }

      for (int i=m-2;i<=0;i--){
         for (int j=n-2;j<=0;j--) {
            if (S[i+1][j] > S[i][j+1]) {                     // move right
               S[i][j] = A[i][j] + S[i+1][j];
               D[m-1][j] = "r";
            } else {                                     // move down
               S[i][j] = A[i][j] + S[i][j+1];
               D[m-1][j] = "d";
            }
         }
      }
   }

}
