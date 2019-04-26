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
    private static void printMatrix(int[][] array){
        for (int i = 0;i<array.length;i++){             // interates through each row
            for (int j = 0;j<array[0].length;j++){      // prints all but last value of row
                System.out.print(array[i][j] + " ");
            }
            System.out.println();  // prints last value of row
        }
    }

    // builds array from input file
    private static int[][] buildMatrix(Scanner file){
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

    private static int[][] matrixSum(int[][] A, int[][] B, int n){
        int[][] C = new int[n][n];

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    private static void copyMatrix(int[][] C, int[][] C11, int[][] C12, int[][] C21, int[][] C22, int n){
        for (int i = 0; i < n;i++){
            if (i < n/2) {
                for (int j = 0; j < n; j++) {
                    if (j < n/2){
                        C[i][j] = C11[i][j];
                    }
                    else{
                        C[i][j] = C12[i][j-(n/2)];
                    }
                }
            }
            else{
                for (int j = 0; j < n; j++){
                    if (j < n/2){
                        C[i][j] = C21[i-(n/2)][j];
                    }
                    else{
                        C[i][j] = C22[i-(n/2)][j-(n/2)];
                    }
                }
            }                               // I organized it this way to minimize if statements per loop
        }
    }

    private static int[][] matrixProduct_DAC(int[][] A, int startrowA, int startcolA,
                                             int[][] B, int startrowB, int startcolB, int n){
        int[][] C = new int[n][n];
        if (n == 1){
            C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
        }
        else {
            int[][] C11 = matrixSum(
                          matrixProduct_DAC(A, startrowA, startcolA, B, startrowB, startcolB, n/2),                                                             // A11 * B11
                          matrixProduct_DAC(A, startrowA, startcolA + n/2, B, startrowB + n/2, startcolB, n/2),                                // A12 * B21
                          n/2);

            int[][] C12 = matrixSum(
                          matrixProduct_DAC(A, startrowA, startcolA, B, startrowB, startcolB + n/2, n/2),                                               // A11 * B12
                          matrixProduct_DAC(A, startrowA, startcolA + n/2, B, startrowB + n/2, startcolB + n/2, n/2),                  // A12 * B22
                          n/2);

            int[][] C21 = matrixSum(
                          matrixProduct_DAC(A, startrowA + n/2, startcolA, B, startrowB, startcolB, n/2),                                               // A21 * B11
                          matrixProduct_DAC(A, startrowA + n/2, startcolA + n/2, B, startrowB + n/2, startcolB, n/2),                  // A22 * B21
                          n/2);

            int[][] C22 = matrixSum(
                          matrixProduct_DAC(A, startrowA + n/2, startcolA, B, startrowB, startcolB + n/2, n/2),                                 // A21 * B12
                          matrixProduct_DAC(A, startrowA + n/2, startcolA + n/2, B, startrowB + n/2, startcolB + n/2, n/2),    // A22 * B22
                          n/2);

            copyMatrix(C, C11, C12, C21, C22, n);
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
            return matrixProduct_DAC(A, 0, 0, B, 0, 0, A.length);
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

            int[][] A = buildMatrix(file);
            int[][] B = buildMatrix(file);

            System.out.println("\nArray A:");
            printMatrix(A);
            System.out.println("\nArray B:");
            printMatrix(B);

            int[][] C = matrixProduct_DAC(A, B);
            System.out.println("\nArray C:");
            printMatrix(C);
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