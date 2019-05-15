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
        System.out.print("Enter # of coins and their value in DECREASING order: ");
        int k = cmdline.nextInt();
        int[] d = new int[k];
        for (int i=0;i<k;i++){
            d[i] = cmdline.nextInt();
        }
        System.out.print("Enter starting n: ");
        int n = cmdline.nextInt();
    }

    public static int[] change_DP(int n, int[] d){
        return d;
    }
}