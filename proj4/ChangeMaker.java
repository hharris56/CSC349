// Hunter Harris, Garret DeAngelis
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;

public class ChangeMaker{

    public static void main(String[] args){
        Scanner cmdline = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String inputfile = cmdline.nextLine();
        Scanner file;
        try {
            file = new Scanner(filepath);
            Path filepath = Paths.get(inputfile);
        }
        catch (IOException e){
            System.out.println("File not found");
        }
    }

    public static int[] change_DP(int n, int[] d){

    }
}