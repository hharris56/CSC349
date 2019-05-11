// Hunter Harris, Garret DeAngelis
// CSC 349, Spring 2019

// scanner import
import java.util.Scanner;
// path import
import java.nio.file.*;
// exception imports
import java.io.IOException;

public class FactoryProblem{

    // main method
    // reads input file + builds problem
    // call methods to solve + output problem
    public static void main(String[] args){
        Scanner cmdline = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String inputfile = cmdline.nextLine();
        Scanner file;
        try{
            Path filepath = Paths.get(inputfile);
            file = new Scanner(filepath);

            // variable definitons
            int n = file.nextInt();                         // number of stations
            int[] e = new int[2];                           // entrance costs
            int[] x = new int[2];                           // exit costs
            int[][] line = new int[2][n];                   // station costs
            int[][] t = new int[2][n-1];                    // transfer costs

            // reading in variables (building problem)
            readInto(e, file, 2);
            readInto(x, file, 2);
            readInto(line[0], file, n);
            readInto(line[1], file, n);
            readInto(t[0], file, n-1);
            readInto(t[1], file, n-1);

            int[][] l = solveProblem(line, t, e, x, n);
            printSolution(l);
        }
        catch(IOException e){
            System.out.println("File not found");
        }
    }

    // reads values from file into given array
    private static void readInto(int[] A, Scanner file, int n){
        for (int i=0;i<n;i++){
            A[i] = file.nextInt();
        }
    }

    // iteratively finds shortest route by filling in both rows in parrallel (the same time)
    // returns directional array that also holds each line total in the respective index 0
    private static int[][] solveProblem(int[][] line, int[][] t, int[] e, int[] x, int n){
        int[][] f = new int[2][n];      // sum martix
        int[][] l = new int[2][n];      // path matrix

        f[0][0] = e[0] + line[0][0];                                // assign first two values in row
        f[1][0] = e[1] + line[1][0];                                // these are entrance values (constant)

        for (int i=1;i<n;i++){                                      // loops through all n values filling in both rows at same time
            if (f[0][i-1] < f[1][i-1] + t[1][i-1]){                 // on line 1, came from line 1
                f[0][i] = f[0][i-1] + line[0][i];
                l[0][i] = 0;
            } else {                                                // on line 1, came from line 2
                f[0][i] = f[1][i - 1] + t[1][i - 1] + line[0][i];
                l[0][i] = 1;
            }
            if (f[1][i-1] < f[0][i-1] + t[0][i-1]){                 // on line 2, came from line 2
                f[1][i] = f[1][i-1] + line[1][i];
                l[1][i] = 1;
            } else {                                                // on line 2, came from line 1
                f[1][i] = f[0][i-1] + t[0][i-1] + line[1][i];
                l[1][i] = 0;
            }
        }

        f[0][n-1] = f[0][n-1] + x[0];                               // add exit values to final total
        f[1][n-1] = f[1][n-1] + x[1];                               // no option to transfer (constant)

        l[0][0] = f[0][n-1];            // place last values of total array into first indexes of directonal array
        l[1][0] = f[1][n-1];            // first indexes not used so no data corruption + easier parameter handling
        return l;
    }

    // wrapper to print solution, prints fastest time + calls printPath
    private static void printSolution(int[][] l){
        int min;
        int lineStart;
        if (l[0][0] < l[1][0]){         // finds minium value
            min = l[0][0];
            lineStart = 0;              // assigning starting row for printPath
        } else {
            min = l[1][0];
            lineStart = 1;
        }
        System.out.println("\nFastest time is: " + min);
        System.out.println("The optimal route is:");
        printPath(l, lineStart, l[0].length);
    }

    // recursively prints path by following directional array
    private static void printPath(int[][] l, int line, int n){
        if (n == 1){
            System.out.println("station "+n+", line "+(line+1));
        } else {
            int prevN = n;
            int prevLine = line;
            printPath(l, l[line][n-1],n-1);
            System.out.println("station "+prevN+", line "+(prevLine+1));
        }
    }


    // prints out matrix in row/col format (INTEGER VALUES)
    private static void printMatrix(int[][] matrix){
        for (int i = 0;i<matrix.length;i++){               // interates through each row
            for (int j = 0;j<matrix[0].length;j++){         // prints all but last value of row
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();                           // prints last value of row
        }
        System.out.println();
    }

    // prints out array
    private static void printArray(int[] array){
        for (int i = 0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}