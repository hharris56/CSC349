// Hunter Harris
// CPE 315, Spring 2019

// Scanner import
import java.util.Scanner;
// Path import
import java.nio.file.*;
// Exceptions imports
import java.io.IOException;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.IllegalArgumentException;

public class MatrixWork{
/*
    public static int[][] matrixProduct(int[][] A, int[][] B){
        if (A[0].length != B.length){

        }
    }
*/
    // prints out array in row/col format
    private static void printArray(int[][] array){
        for (int i = 0;i<array.length;i++){             // interates through each row
            for (int j = 0;j<array[0].length;j++){      // prints all but last value of row
                System.out.print(array[i][j] + " ");
            }
            System.out.println();  // prints last value of row
        }
    }

    // builds array from input file
    private static int[][] buildArray(Scanner file){
        int rows = file.nextInt(), cols = file.nextInt();   // first two values are row and col count
        int[][] array = new int[rows][cols];

        for (int i = 0; i < rows*cols;i++){             // gets row * col values from file
            array[i/cols][i%cols] = file.nextInt();     // places them in 2D array
        }

        return array;
    }

    public static int[][] matrixProduct(int[][] A, int[][] B){
        int rowsA = A.length, rowsB = B.length;
        if (rowsA == 0 || rowsB == 0){                  // check to make sure we can index to 0 in arrays
            return new int[0][0];                       // otherwise return empty array
        }
        int colsA = A[0].length, colsB = B[0].length;
        if (colsA != rowsB){                            // check that matricies are right dimensions
            throw new IllegalArgumentException();
        }
        int[][] C = new int[rowsA][colsB];              // declare solution matrix
        for (int i=0;i<rowsA;i++){                      // matrix multiplication algorithm
            for (int j=0;j<colsB;j++){
                C[i][j] = 0;
                for (int k = 0;k<colsA;k++){
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] matrixProduct_DAC(int[][] A, int startrowA, int startcolA,
                                             int[][] B, int startrowB, int startcolB, int n){
        int[][] C = new int[n][n];
        if (n == 1){
            C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
        }
        else {
            int[][] C11 = matrixProduct_DAC(A, 0, 0,
                                            B, 0, 0, n/2);
            int[][] C12 = matrixProduct_DAC(A, 0, startcolA/2+1,
                                            B, 0, startcolB/2+1, n/2);
            int[][] C21 = matrixProduct_DAC(A, startrowA/2, 0,
                                            B, startrowB/2, 0, n/2);
            int[][] C22 = matrixProduct_DAC(A, startrowA/2, startcolA/2,
                                            B, startrowB/2, startcolB/2, n/2);
        }
        return C;
    }

    public static int[][] matrixProduct_DAC(int[][] A, int[][]B){
        int rowA = A.length, rowB = B.length;
        if (rowA < 1 || rowB < 1){                      // check to make sure we can index to 0 in arrays
            return new int[0][0];                       // otherwise return empty array
        }
        int colA = A[0].length, colB = B[0].length;
        if ((rowA == rowB) && (colA == colB) && (rowA == rowB)){    // check that matricies are square and equal
            //return matrixProduct_DAC(A, 0, 0, B, 0, 0, A.length);
        }
        throw new IllegalArgumentException();
    }


    public static void main(String[] args){
        Scanner cmdLine = new Scanner(System.in);   // set up scanner for cmdline
        System.out.print("Enter filename: ");       // prompt for filename
        String inputfile = cmdLine.nextLine();      // read in filename
        Scanner file;
        try{
            Path filePath = Paths.get(inputfile);   // get file path
            file = new Scanner(filePath);           // new scanner to read from file

            int[][] A = buildArray(file);
            int[][] B = buildArray(file);

            System.out.println("\nArray A:");
            printArray(A);
            System.out.println("\nArray B:");
            printArray(B);

            int[][] C = matrixProduct(A, B);
            System.out.println("\nArray C:");
            printArray(C);
        }
        catch(IOException e){
            System.out.println("File not found");
            return;
        }

        catch(IllegalArgumentException e){
            System.out.println("# of cols in A must = # of rows in B");
            return;
        }

    }

}