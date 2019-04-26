// Hunter Harris , Garrett DeAngelis (gsdeange)
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

    // sums 2 matricies, returns result
    private static int[][] matrixSum(int[][] A, int[][] B, int n){
        int[][] C = new int[n][n];

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    // sums 2 matricies, returns result, allows for different starting indexes
    private static int[][] matrixSum(int[][] A, int startrowA, int startcolA,
                                     int[][] B, int startrowB, int startcolB, int n){
        int[][] C = new int[n][n];

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                C[i][j] = A[i+startrowA][j+startcolA] + B[i+startrowB][j+startcolB];
            }
        }

        return C;
    }

    // subtracts 2 matricies, returns result
    private static int[][] matrixDifference(int[][] A, int[][] B, int n){
        int[][] C = new int[n][n];

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;
    }

    // subtracts 2 matricies, returns result, allows for different starting indexes
    private static int[][] matrixDifference(int[][] A, int startrowA, int startcolA,
                                            int[][] B, int startrowB, int startcolB, int n){
        int[][] C = new int[n][n];

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                C[i][j] = A[i+startrowA][j+startcolA] - B[i+startrowB][j+startcolB];
            }
        }

        return C;
    }

    // copies 4 matrices into 1
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
            }                               // I organized it this way to minimize if statements within loop
        }
    }

    // recursive method, computes matrix product using strassen method
    private static int[][] matrixProduct_Strassen(int[][] A, int startrowA, int startcolA,
                                                  int[][] B, int startrowB, int startcolB, int n){
        int[][] C = new int[n][n];
        if (n == 1){
            C[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
        }
        else {

            // Getting matricies 1-7
            int[][] P1 = matrixProduct_Strassen(                                                                                                            // A11(B12 - B22)
                    A, startrowA, startcolA,
                    matrixDifference(B, startrowB, startcolB + n/2,
                                     B, startrowB + n/2, startcolB + n/2, n/2), 0, 0,
                    n/2);

            int[][] P2 = matrixProduct_Strassen(                                                                                                            // (A11 + A12)B22
                    matrixSum(A, startrowA, startcolA,
                              A, startrowA, startcolA + n/2, n/2), 0, 0,
                    B, startrowB + n/2, startcolB + n/2,
                    n/2);

            int[][] P3 = matrixProduct_Strassen(                                                                                                            // (A21 + A22)B11
                    matrixSum(A, startrowA + n/2, startcolA,
                              A, startrowA + n/2, startcolA + n/2, n/2), 0, 0,
                    B, startrowB, startcolB,
                    n/2);

            int[][] P4 = matrixProduct_Strassen(
                    A, startrowA + n/2, startcolA + n/2,
                    matrixDifference(B, startrowB + n/2, startcolB,
                                     B, startrowB, startcolB, n/2), 0, 0,
                    n/2);                                                                                                                                   // A22(B21 - B11)

            int[][] P5 = matrixProduct_Strassen(
                    matrixSum(A, startrowA, startcolA,
                              A, startrowA + n/2, startcolA + n/2, n/2), 0, 0,
                    matrixSum(B, startrowB, startcolB,
                              B, startrowB + n/2, startcolB + n/2, n/2), 0, 0,
                    n/2);                                                                                                                                   // (A11 + A22)(B11 + B22)

            int[][] P6 = matrixProduct_Strassen(
                    matrixDifference(A, startrowA, startcolA + n/2,
                                     A, startrowA + n/2, startcolA + n/2, n/2), 0, 0,
                    matrixSum(B, startrowB + n/2, startcolB,
                              B, startrowB + n/2, startcolB + n/2, n/2), 0, 0,
                    n/2);                                                                                                                                  // (A12 - A22)(B21 + B22)

            int[][] P7 = matrixProduct_Strassen(
                    matrixDifference(A, startrowA, startcolA,
                                     A, startrowA + n/2, startcolA, n/2), 0, 0,
                    matrixSum(B, startrowB, startcolB,
                              B, startrowB, startcolB + n/2, n/2), 0, 0,
                    n/2);                                                                                                                                 // (A11 - A21)(B11 + B12)

            // Getting C11, C12...
            int[][] C11 = matrixDifference(
                    matrixSum(P5, P4, n/2),
                    matrixSum(P2, P6, n/2),
                    n/2);                                                                                                                                   // P5 + P4 - P2 + P6

            int[][] C12 = matrixSum(P1, P2, n/2);                                                                                                           // P1 + P2

            int[][] C21 = matrixSum(P3, P4, n/2);                                                                                                           // P3 + P4

            int[][] C22 = matrixDifference(
                    matrixSum(P1, P5, n/2),
                    matrixDifference(P3, P7, n/2),
                    n/2);                                                                                                                                   // P1 + P5 - P3 - P7

            copyMatrix(C, C11, C12, C21, C22, n);
        }
        return C;
    }

    // strassen wrapper
    public static int[][] matrixProduct_Strassen(int[][] A, int[][]B){
        int rowA = A.length, rowB = B.length;
        if (rowA < 1 || rowB < 1){                      // check to make sure we can index to 0 in arrays
            throw new IllegalArgumentException();       // otherwise throw exception
        }
        int colA = A[0].length, colB = B[0].length;
        if (!((rowA == rowB) && (colA == colB) && (rowA == rowB))){    // check that matricies are square and equal
            throw new IllegalArgumentException();
        }
        int sizeA = A.length*A.length;
        if (!((sizeA & (sizeA-1)) == 0)){                              // check that matricies are powers of 2
            throw new IllegalArgumentException();
        }
        return matrixProduct_Strassen(A, 0, 0, B, 0, 0, A.length);
    }

    // recursive method, computes matrix product using DAC method
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

    // DAC wrapper
    public static int[][] matrixProduct_DAC(int[][] A, int[][]B){
        int rowA = A.length, rowB = B.length;
        if (rowA < 1 || rowB < 1){                      // check to make sure we can index to 0 in arrays
            throw new IllegalArgumentException();       // otherwise throw exception
        }
        int colA = A[0].length, colB = B[0].length;
        if (!((rowA == rowB) && (colA == colB) && (rowA == rowB))){    // check that matricies are square and equal
            throw new IllegalArgumentException();
        }
        int sizeA = A.length*A.length;
        if (!((sizeA & (sizeA-1)) == 0)){                              // check that matricies are powers of 2
            throw new IllegalArgumentException();
        }
        return matrixProduct_DAC(A, 0, 0, B, 0, 0, A.length);
    }

    // main method
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

            /**************/
            int[][] C = matrixProduct_Strassen(A, B);
            /**************/

            System.out.println("\nArray C:");
            printMatrix(C);
        }
        catch(IOException e){
            System.out.println("File not found");
            return;
        }

        catch(IllegalArgumentException e){
            System.out.println("\nInvalid Matrix Size");
            return;
        }
    }
}
