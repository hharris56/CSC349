// Garrett DeAngelis, Hunter Harris
// CSC 349, Spring 2019
// Project 5 (Graphs)

import java.util.LinkedList;
import java.util.Iterator;
import java.lang.Error;

public class DiGraph {
   private static LinkedList<Integer>[] graph;

   // constructor
   public DiGraph(int N) {
      graph = new LinkedList[N];
      for(int i = 0; i<N; i++) {                                        // initialize vertices
         LinkedList<Integer> vertices = new LinkedList<Integer>();
         graph[i] = vertices;
      }
   }

   // adds edge to DiGraph
   public static void addEdge(int from, int to) {
      if(graph[from-1].indexOf(to) < 0) {                               // check if edge exists, add if it doesnt
         graph[from-1].addLast(to);
         System.out.println("edge added from " + from + " to " + to);
      }
   }

   // removes edge from DiGraph
   public static void deleteEdge(int from, int to) {
      int index = graph[from-1].indexOf(to);
      if(index > -1) {                                                  // check if edge exists, delete if it does
         graph[from-1].remove(index);
         System.out.println("edge removed from " + from + " to " + to);
      }
   }

   // returns total number of edges in DiGraph
   public static int edgeCount() {
      int edgeCount = 0;
      for(int i = 0; i<graph.length; i++) {
         edgeCount = edgeCount + graph[i].size();
      }
      return edgeCount;
   }

   // returns total number of vertices in DiGraph
   public static int vertexCount()
   {
      return graph.length;
   }

   // prints out DiGraph
   public void print() {
      System.out.println();
      for (int i = 0; i < graph.length; i++) {
         System.out.print((i + 1) + " is connected to: ");
         boolean first = true;                                 // used for string formatting
         for (int j = 0; j < graph[i].size(); j++) {           // only prints comma if not first value
            if (first != true) {
               System.out.print(", ");
            } else {
               first = false;
            }
            System.out.print(graph[i].get(j));
         }
         System.out.println();
      }
   }

   // counts the number of connections a vertex RECEIVES
   // used for topological sorting
   private static int[] indegrees() {
      int n = graph.length;
      int[] in = new int[n];
      for(int i = 0; i<n; i++)
         in[i] = 0;

      for(int i = 0; i<n; i++) {
         for(int j = 0; j<graph[i].size(); j++) {
            in[graph[i].get(j)-1]++;
         }
      }
      return in;
   }

   // topological sort of the graph
   public static int[] topSort() {
      int N = graph.length;
      int[] in = indegrees();
      int[] A = new int[N];
      LinkedList<Integer> queue = new LinkedList<Integer>();

      for(int u = 0; u<N; u++) {       // start by adding all vertices of 0 degrees to queue
         if(in[u] == 0)
            queue.offer(u+1);
      }

      int i = 0;
      while(!queue.isEmpty()) {
         int u = queue.remove();
         A[i] = u;                                          // place vertex in array
         i++;                                               // increment index for next vertex
         for(int v = 0; v<graph[u-1].size(); v++) {
            in[graph[u-1].get(v)-1]--;                      // decrement indegree of connected vertices
            if(in[graph[u-1].get(v)-1] == 0)                // if vertex now has indegree of 0, add to queue
               queue.offer(graph[u-1].get(v));
         }
      }

      if(i != N)                                            // check for cyclic graph (topological sort impossible)
         throw new ArithmeticException("Graph is cyclic!");
      return A;
   }

   private class VertexInfo {
      int distance, parent;   // parent in NATURAL INDEXING
      public VertexInfo(int distance1, int predeccesor) {
         distance = distance1;
         parent = predeccesor;
      }
      public String toString(int num)
      {
         return "node " + num + ": " + "distance: " + distance + " parent: " + parent;
      }
   }

   // Breadth-first search of graph
   // calculates BFS of the graph starting from s vertex
   // returns array of VertexInfo
   private VertexInfo[] BFS(int s) {
      int n = graph.length;
      VertexInfo[] vertices = new VertexInfo[n];
      // use addLast to add, removeFirst to remove
      LinkedList<Integer> queue = new LinkedList<Integer>();

      for(int i = 0; i<n; i++) {
         VertexInfo vertex = new VertexInfo(-1, -1);
         vertices[i] = vertex;
      }
      vertices[s-1].distance = 0;                                          // convert s from NATURAL to WHOLE for indexing
      queue.addLast(s);
      while(!queue.isEmpty()) {
         int u = queue.removeFirst();                                      // dequeue
         for(int v = 0; v<graph[u-1].size(); v++) {
            int adjIndex = graph[u-1].get(v)-1;
            if(vertices[adjIndex].distance == -1) {                        // if distance is not yet set
               vertices[adjIndex].distance = vertices[u-1].distance+1;     // set distance to current vertex + 1
               vertices[adjIndex].parent = u;                              // set parent to current vertex
               queue.addLast(adjIndex+1);                                  // increment by one because it represents the node, not needed for index
            }
         }
      }
      return vertices;
   }

   // checks if path exists between two vertices
   public boolean isTherePath(int from, int to) {
      VertexInfo[] vertices = BFS(from);
      for(int i = 0; i<vertices.length; i++) {
         System.out.println(vertices[i].toString(i+1));
      }
      if(vertices[to-1].distance == -1)
         return false;
      return true;
   }

   public int lengthOfPath(int from, int to) {
      //test to see if there is a validPath
      if(from == to)
         return 0;

      if(isTherePath(from, to) == false)
         return -1;

      int minLength = 1;                        //init minLength to something really large by default
      VertexInfo[] vertices = BFS(from);

      VertexInfo current = vertices[to-1];     //ending node --> retrace our steps to target
      int currentIndex = to;

      while(current.parent != from) {
         System.out.println("current: " + currentIndex + " parent: " + current.parent);
         minLength++;
         current = vertices[current.parent-1];
         currentIndex = current.parent;
      }
      return minLength;
   }

   public void printPath(int from, int to) {
      if(isTherePath(from, to) == false || from == to)   //handles the edge cases
         System.out.println("There is no path");
      else {
         int pathLength = lengthOfPath(from, to);
         int[] backwardList = new int[pathLength+1];     //create a backwards list that is one greater than the paths
         //System.out.println("backwardList length: " + backwardList.length);
         VertexInfo[] vertices = BFS(from);

         VertexInfo current = vertices[to-1];
         int currentIndex = to;
         int index = pathLength;

         while(current.parent != -1) {
            backwardList[index] = currentIndex;
            System.out.println("inserting " + currentIndex + " to backwardList at index: " + index);
            index--;
            currentIndex = current.parent;
            current = vertices[current.parent-1];
         }
         backwardList[index] = currentIndex;

         for(int i = 0; i<pathLength; i++) {
            System.out.print(backwardList[i] + " --> ");
         }
         System.out.println(backwardList[pathLength]);
      }
   }

   // Class TreeNode -----------------------------------------
   private class TreeNode{
      int vert;
      LinkedList<TreeNode> children;

      public TreeNode(int n){
         this.vert = n;
         this.children = new LinkedList<TreeNode>();
      }
   }

   private TreeNode buildTree(int s){
      VertexInfo[] vertInfo = BFS(s);           // obtain vertex data using BFS
      int n = vertInfo.length;
      TreeNode[] nodes = new TreeNode[n];

      for (int i=0;i<n;i++){
         nodes[i] = new TreeNode(i+1); // <--- set vertices using NATURAL numbers
      }

      for (int i=0;i<n;i++){
         int parentIndex = vertInfo[i].parent - 1; // <--- subtract to convert NATURAL to WHOLE
         if (parentIndex > -1) {
            TreeNode parent = nodes[parentIndex];
            TreeNode child = nodes[i];
            parent.children.add(child);
         }
      }

      return nodes[s-1];
   }

   // printTree wrapper
   // calls buildTree and passes root to printTreeRecur
   public void printTree(int s){
      System.out.println();
      TreeNode root = buildTree(s);
      printTreeRecur(root, 0);
   }

   // recursively prints tree using TreeNode type objects
   // level determines how far to indent
   private void printTreeRecur(TreeNode node, int level){
      for (int i=0;i<level;i++){
         System.out.print("    ");                             // indent to match level
      }
      System.out.println(node.vert);                           // print node value (vertex number)
      for (int i=0;i<node.children.size();i++){
         printTreeRecur(node.children.get(i), level+1);  // recursive call on children
      }
   }

}