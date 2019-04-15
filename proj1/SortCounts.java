// Hunter Harris (hvharris), Garrett DeAngelis (gsdeange)
// CSC 349 - Spring 2019

public class SortCounts {

    // each "run" method generates 100 random lists of size N and runs them while
    // summing up total comparisons. returns average value by dividing by 100 at the end
    private static long runSelection(int N){
        long average = 0;
        for (int i=0;i<100;i++){
            int[] test = TestCases.makeUnsorted(N);
            average += Sorts1.selectionSort(test, N);
        }
        return average/100;
    }

    // read documentation above
    private static long runMerge(int N){
        long average = 0;
        for (int i=0;i<100;i++){
            int[] test = TestCases.makeUnsorted(N);
            average += Sorts1.mergeSort(test, N);
        }
        return average/100;
    }

    // read documentation above
    private static long runQuick(int N){
        long average = 0;
        for (int i=0;i<100;i++){
            int[] test = TestCases.makeUnsorted(N);
            average += Sorts1.quickSort(test, N);
        }
        return average/100;
    }

    // counts number of comparisons for all sorting types using arrays starting at
    // size 100 and * 2 until 12800 inclusive
    public static void main(String[] args){
        int N;
        long compares;
        for(N=100;N<=12800;N=N*2){
            System.out.print("N=" + N);
            compares = runSelection(N);
            System.out.print(": C_ss=" + compares);
            compares = runMerge(N);
            System.out.print(", C_ms=" + compares);
            compares = runQuick(N);
            System.out.println(", C_qs=" + compares);
        }
    }
}
