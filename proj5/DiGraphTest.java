// Garrett DeAngelis, Hunter Harris
// CSC 349, Spring 2019
// Project 5 (Graphs)

import java.util.LinkedList;
import java.util.Scanner;

public class DiGraphTest
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter the number of vertices:");
      int numVertices = scan.nextInt();
      scan.nextLine();
      DiGraph graph = new DiGraph(numVertices);

      //print the menu
      System.out.println("Choose one of the following operations:");
      System.out.println("- add edge (enter a)");
      System.out.println("- delete edge (enter d)");
      System.out.println("- edge count (enter e)");
      System.out.println("- vertex count (enter v)");
      System.out.println("- print graph (enter p)");
      System.out.println("- topological sort");
      System.out.println("- Quit (enter q)");

      boolean run = true;
      while(run)
      {
         //still issue with a 4 5 input
         String response = scan.nextLine();
         if(response.length() != 1) {
            System.out.println("answer too long");
            response = "x";
         }

         switch(response)
         {
            case "a":
               System.out.println("Enter the vertices you would like to add an edge between (to, from):");
               int to = scan.nextInt();
               int from = scan.nextInt();
               scan.nextLine();
               graph.addEdge(to, from);
               break;

            case "d":
               System.out.println("Enter the vertices you would like to delete an edge between (from, to):");
               from = scan.nextInt();
               to = scan.nextInt();
               scan.nextLine();
               graph.deleteEdge(from, to);
               break;

            case "e":
               System.out.println("Number of edges: " + graph.edgeCount());
               break;

            case "v":
               System.out.println("Number of vertices: " + graph.vertexCount());
               break;

            case "p":
               graph.print();
               break;

            case "t":
               printTopo(graph);
               break;

            case "i":
               System.out.println("Enter the vertices you would like to check the path (from, to):");
               from = scan.nextInt();
               to = scan.nextInt();
               scan.nextLine();
               boolean result = graph.isTherePath(from, to);
               System.out.println("is there a path: " + result);
               break;

            case "l":
               System.out.println("Enter the vertices you would like to check the path (from, to):");
               from = scan.nextInt();
               to = scan.nextInt();
               scan.nextLine();
               int length = graph.lengthOfPath(from, to);
               System.out.println("The length of the path is: " + length);
               break;

            case "s":
               System.out.println("Enter the vertices you would like to check the path (from, to):");
               from = scan.nextInt();
               to = scan.nextInt();
               scan.nextLine();
               graph.printPath(from, to);
               break;

            case "b":
               System.out.print("Enter source vertex: ");
               int source = scan.nextInt();
               scan.nextLine();
               graph.printTree(source);
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
   private static void printTopo(DiGraph graph) {
      System.out.print("topo sorting: ");
      try {
         int[] result = graph.topSort();
         for(int i = 0; i<result.length-1; i++) {
            System.out.print((result[i]) + ", ");
         }
         System.out.println(result[result.length-1]);
      }
      catch(ArithmeticException e) {
         System.out.println("Graph is cyclical!");
      }
   }
}