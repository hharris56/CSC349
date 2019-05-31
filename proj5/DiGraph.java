// Garrett DeAngelis, Hunter Harris
// CSC 349, Spring 2019
// Project 5 (graphs)

import java.util.LinkedList;
import java.util.Iterator;
import java.lang.Error;

public class DiGraph {
   private static LinkedList<Integer>[] graph;

   public DiGraph(int N) {
      graph = new LinkedList[N];
      for (int i = 0; i < N; i++) {
         LinkedList<Integer> vertices = new LinkedList<Integer>();
         graph[i] = vertices;
      }
   }

   // HH - made changed indexOf(to) and addLast(to) to indexOf(to-1) and
   // addLast(to-1) for
   // consistancy with numbering (always add 1 to get natural index)
   public void addEdge(int from, int to) {
      if (graph[from - 1].indexOf(to - 1) < 0) { // then edge is missing from that graph, add it to the beginning
         graph[from - 1].addLast(to - 1);
      }
      // System.out.println("edge from " + from + " to " + to);
   }

   public void deleteEdge(int from, int to) {
      int index = graph[from - 1].indexOf(to - 1);
      if (index > -1) { // slight change for 1 less comparison (> as opposed to >=)
         graph[from - 1].remove(index);
      }
   }

   public int edgeCount() {
      int edgeCount = 0;
      for (int i = 0; i < graph.length; i++) {
         edgeCount = edgeCount + graph[i].size();
      }
      return edgeCount;
   }

   public int vertexCount() {
      return graph.length;
   }

   public static void oldPrint() {
      // prints each vertex and its connected nodes
      System.out.println();
      System.out.println("The graph is the following: ");
      for (int i = 0; i < graph.length; i++) {
         System.out.print(i + 1 + " is connected to: ");
         // System.out.print((i+1) + " is connected to: ");
         for (int j = 0; j < graph[i].size() - 1; j++) {
            System.out.print((graph[i].get(j)) + ", ");
         }
         if (graph[i].size() > 0)
            System.out.print(graph[i].getLast());
         System.out.println();
      }
   }

   public void print() {
      System.out.println();
      for (int i = 0; i < graph.length; i++) {
         System.out.print((i + 1) + " is connected to: ");
         boolean first = true;
         for (int j = 0; j < graph[i].size(); j++) {
            if (first != true) {
               System.out.print(", ");
            } else {
               first = false;
            }
            System.out.print((graph[i].get(j) + 1));
         }
         System.out.println();
      }
   }

   // used for topological sorting
   private int[] indegrees() {
      int n = graph.length;
      int[] in = new int[n];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < graph[i].size();j++) {
            in[graph[i].get(j)]++;
         }
      }
      return in;
   }
/*
   // returns topologically sorted vertices
   public int[] topSort() {
      LinkedList<Integer> queue = new LinkedList<Integer>();
      int n = graph.length;
      int[] in = indegrees();
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
         if (in[i] == 0)
            queue.add(i);
      }
      int i = 0;
      while (queue.size() != 0) {
         int temp = queue.remove();
         A[i] = temp;
         i++;
         for (int j = 0; j < graph[temp].size(); j++) {
            in[j] = in[j] - 1;
            if (in[j] == 0)
               queue.add(j);
         }
      }
      if (i == n + 1)
         throw new ArithmeticException("Graph is cyclical!");
      return A;
   }

   public int[] topSort2() {
      int N = graph.length;
      int[] in = indegrees();
      int[] A = new int[N];
      LinkedList<Integer> queue = new LinkedList<Integer>();

      System.out.println("N:" + N);
      for (int u = 0; u < N; u++) {
         if (in[u] == 0)
            queue.offer(u);
      }

      int i = 0;
      System.out.println("queue size: " + queue.size());
      while (!queue.isEmpty()) {
         int u = queue.remove();
         A[i] = u;
         i++;
         System.out.print(u + ": ");
         for (int v = 0; v < graph[u].size(); v++) {
            System.out.print(+graph[u].get(v) - 1 + " ");
            in[graph[u].get(v) - 1]--;
            // in[v] = in[v]-1;
            if (in[graph[u].get(v) - 1] == 0)
               queue.offer(v);
         }
         System.out.println();
      }

      System.out.println("N length: " + N);
      System.out.println("i: " + i);
      // if(i != N+1)
      // throw new ArithmeticException("Graph is cyclical!");

      return A;
   }
*/
   public int[] topSort(){
      int n = graph.length;
      int[] in = indegrees();
      int[] result = new int[n];
      LinkedList<Integer> Q = new LinkedList();

      for (int i=0;i<n;i++){
         if (in[i] == 0){
            Q.addLast(i);
         }
      }

      int i = 0;
      while (!Q.isEmpty()){
         Integer curr = Q.removeFirst();
         result[i] = curr + 1;
         for (int j=0;j<graph[curr].size();j++){
            int node = graph[curr].get(j);
            in[node]--;
            if (in[node] == 0){
               Q.addLast(node);
            }
         }
         i++;
      }
      if (i != n){
         throw new IllegalArgumentException();
      }
      return result;
   }
}