import java.util.Scanner;

public class MatrixWork{
/*
    public static int[][] matrixProduct(int[][] A, int[][] B){
        if (A[0].length != B.length){

        }
    }
*/
    public static void main(String[] args){
        Scanner cmdLine = new Scanner(System.in);   // set up scanner for cmdline
        System.out.println("Enter filename");       // prompt for filename
        String inputfile = cmdLine.nextLine();      // read in filename
        Scanner file = new Scanner(inputfile);      // new scanner to read from file

        int rows = file.nextInt(), cols = file.nextInt();
        int[][] A = new int[rows][cols];

    }

}