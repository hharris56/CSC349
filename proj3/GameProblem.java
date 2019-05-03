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

   private static int[][] buildGane(Scanner file){
      int n = file.nextInt(), m = file.nextInt();
      int[][] game = new int[n][m];

      for (int i=0;i<n*m;i++){
         game[i/n][i%n] = file.nextInt();
      }
      return game;
   }

   public static void game(int m, int n, int[][] game)

}
