// Garrett DeAngelis, Hunter Harris
// CSC 349, Spring 2019
// Project 5 (graphs)

import java.util.LinkedList;
import java.util.Scanner;
import java.lang.IllegalArgumentException;

public class DiGraphTest
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter the number of vertices:");
      int numVertices = scan.nextInt();
      DiGraph graph = new DiGraph(numVertices);
      
      //print the menu
      System.out.println("Choose one of the following operations:");
      System.out.println("- add edge (enter a)");
      System.out.println("- delete edge (enter d)");
      System.out.println("- edge count (enter e)");
      System.out.println("- vertex count (enter v)");
      System.out.println("- print graph (enter p)");
      System.out.println("- topological sort (enter t)");
      System.out.println("- Quit (enter q)");

      boolean run = true;
      while(run)
      {
         String response = scan.next();
         if(response.length() != 1)
            //System.out.println("too long!!!");
            response = "x";
         switch(response)
         {
            case "a":
               System.out.println("Enter the vertices you would like to add an edge between (to, from):");
               int to = scan.nextInt();
               int from = scan.nextInt();
               graph.addEdge(to, from);
               break;

            case "d":
               System.out.println("Enter the vertices you would like to delete an edge between (from, to):");
               from = scan.nextInt();
               to = scan.nextInt();
               graph.deleteEdge(from, to);
               break;

            case "e":
               System.out.println("Number of edges: " + graph.edgeCount());
               break;

            case "v":
               System.out.println("Number of vertices: " + graph.vertexCount());
               break;

            case "p":
               System.out.println("The graph is the following: ");
               graph.print();
               break;

            case "t":
               System.out.println("Topological sort results: ");
               printTopo(graph);
               break;

            case "q":
               System.out.println("Goodbye! :)");
               System.exit(0);
               break;

            default:
               System.out.println("invalid menu choice");
               break;
         }
         System.out.println();
         System.out.println("Enter a menu choice: ");
      }
   }
   //takes in a list of vertices and prints them in the right format
   private static void printTopo(DiGraph graph){
      try {
         int[] sorted = graph.topSort();
         printArray(sorted);
      }
      catch (IllegalArgumentException e){
         System.out.println("The graph is cyclic");
      }

   }

   public static void printArray(int[] A){
      System.out.print("[");
      for (int i=0;i<A.length-1;i++){
         System.out.print(A[i] + ", ");
      }
      System.out.println(A[A.length-1] + "]");
   }
}