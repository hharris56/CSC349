// Hunter Harris, Garret DeAngelis
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;
// library import
import java.util.Random;
import java.util.Arrays;

public class Tests {
    public static void main(String[] args) {
        Random rand = new Random();
        int count = 100;
        if (Arrays.asList(args).contains("-c")){
            Scanner cmdline = new Scanner(System.in);
            System.out.print("How many? ");
            count = cmdline.nextInt();
        }
        int[] coinValues = new int[]{2000, 1000, 500, 100, 25, 10, 5, 1};
        for (int i=0;i<count;i++){
            int changeValue = 1 + rand.nextInt(5000);
            System.out.println(changeValue*.01);
            int[] result = ChangeMaker.change_DP(changeValue, coinValues);
        }
    }
}