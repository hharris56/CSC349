// Hunter Harris
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;

public class GameProblem{

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

}
