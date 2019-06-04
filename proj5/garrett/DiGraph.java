import java.util.LinkedList;
import java.util.Iterator;
import java.lang.Error;

public class DiGraph
{
   private static LinkedList<Integer>[] graph;

   public DiGraph(int N)
   {
      graph = new LinkedList[N];
      for(int i = 0; i<N; i++)
      {
         LinkedList<Integer> vertices = new LinkedList<Integer>();
         graph[i] = vertices;
      }
   }
   public static void addEdge(int from, int to)
   {
      if(graph[from-1].indexOf(to) < 0)
      {
         //then edge is missing from that graph, add it to the beginning
         graph[from-1].addLast(to);
      }
      System.out.println("edge from " + from + " to " + to);
   }
   public static void deleteEdge(int from, int to)
   {
      int index = graph[from-1].indexOf(to);
      //System.out.println("removing at " + (from-1));
      //System.out.println("at index: " + index);
      if(index >= 0)
      {
         graph[from-1].remove(index);
         //System.out.println(graph[from-1].size());
      }
   }
   public static int edgeCount()
   {
      int edgeCount = 0;
      for(int i = 0; i<graph.length; i++)
      {
         edgeCount = edgeCount + graph[i].size();
      }
      return edgeCount;
   }
   public static int vertexCount()
   {
      return graph.length;
   }
   public static void print()
   {
      // prints each vertex and its connected nodes
      System.out.println();
      System.out.println("The graph is the following: ");
      for(int i = 0; i<graph.length; i++)
      {
         System.out.print(i+1 + " is connected to: ");
         //System.out.print((i+1) + " is connected to: ");
         for(int j = 0; j<graph[i].size()-1; j++)
         {
            System.out.print((graph[i].get(j)) + ", ");
         }
         if(graph[i].size() > 0)
            System.out.print(graph[i].getLast());  
         System.out.println();
      }
   }
   //used for topological sorting
   private static int[] indegrees()
   {
      int n = graph.length;
      int[] in = new int[n];
      for(int i = 0; i<n; i++)
         in[i] = 0;

      for(int i = 0; i<n; i++)
      {
         //System.out.print(i+1 + ": ");
         for(int j = 0; j<graph[i].size(); j++)
         {
            //System.out.print(j+1 + " ");
            in[graph[i].get(j)-1]++;
         }
         //System.out.println();
      }
      /*for(int i = 0; i<n; i++)
      {
         System.out.print(in[i] + " ");
      }
      System.out.println();*/

      return in;
   }
   public static int[] topSort()
   {
      int N = graph.length;
      int[] in = indegrees();
      int[] A = new int[N];
      LinkedList<Integer> queue = new LinkedList<Integer>();

      //System.out.println("N:" + N);
      for(int u = 0; u<N; u++)
      {

         if(in[u] == 0)
            queue.offer(u+1);
      }

      int i = 0;
      //System.out.println("queue size: " + queue.size());
      while(!queue.isEmpty())
      {
         int u = queue.remove();
         A[i] = u;
         i++;
         for(int v = 0; v<graph[u-1].size(); v++)
         {
            in[graph[u-1].get(v)-1]--;
            if(in[graph[u-1].get(v)-1] == 0)
               queue.offer(graph[u-1].get(v));
         }
      }
      if(i != N)
         throw new ArithmeticException("Graph is cyclical!");
      return A;
   }
   private class VertexInfo
   {
      int distance, parent;
      public VertexInfo(int distance1, int predeccesor)
      {
         distance = distance1;
         parent = predeccesor;
      }
      public String toString(int num)
      {
         return "node " + num + ": " + "distance: " + distance + " parent: " + parent;
      }
   }
   private VertexInfo[] BFS(int s)
   {
      int n = graph.length;
      VertexInfo[] vertices = new VertexInfo[n];
      // use addLast to add, removeFirst to remove
      LinkedList<Integer> queue = new LinkedList<Integer>();

      for(int i = 0; i<n; i++)
      {
         VertexInfo vertex = new VertexInfo(-1, -1);
         vertices[i] = vertex;
      }
      vertices[s-1].distance = 0; // s will come in as natural numbering--> convert to our indexing
      queue.addLast(s);
      while(!queue.isEmpty())
      {
         int u = queue.removeFirst(); //dequeue
         //System.out.print((u) + ": ");
         for(int v = 0; v<graph[u-1].size(); v++)
         {
            //System.out.print(graph[u-1].get(v) + ", ");
            int adjIndex = graph[u-1].get(v)-1;
            if(vertices[adjIndex].distance == -1)
            {
               vertices[adjIndex].distance = vertices[u-1].distance+1;
               vertices[adjIndex].parent = u;
               queue.addLast(adjIndex+1); //increment by one because it represents the node, not needed for index
            }
         }
         //System.out.println();
      }
      return vertices;
   }
   public boolean isTherePath(int from, int to)
   {
      VertexInfo[] vertices = BFS(from);
      /*for(int i = 0; i<vertices.length; i++)
      {
         System.out.println(vertices[i].toString(i+1));
      }*/
      if(vertices[to-1].distance == -1)
         return false;
      return true;
   }
   public int lengthOfPath(int from, int to)
   {
      //test to see if there is a validPath
      if(isTherePath(from, to) == false || from == to)
         return 0;

      int minLength = 1; //init minLength to something really large by default
      VertexInfo[] vertices = BFS(from);

      VertexInfo current = vertices[to-1];     //ending node --> retrace our steps to target
      int currentIndex = to;

      while(current.parent != from)
      {
         System.out.println("current: " + currentIndex + " parent: " + current.parent);
         minLength++;
         current = vertices[current.parent-1];
         currentIndex = current.parent;
      }
      return minLength;
   }
   public void printPath(int from, int to)
   {
      if(isTherePath(from, to) == false || from == to)   //handles the edge cases
         System.out.println("There is no path");
      else
      {
         int pathLength = lengthOfPath(from, to);
         int[] backwardList = new int[pathLength+1];     //create a backwards list that is one greater than the paths
         //System.out.println("backwardList length: " + backwardList.length);
         VertexInfo[] vertices = BFS(from);

         VertexInfo current = vertices[to-1];
         int currentIndex = to;
         int index = pathLength;

         while(current.parent != -1)
         {
            backwardList[index] = currentIndex;
            System.out.println("inserting " + currentIndex + " to backwardList at index: " + index);
            index--;
            currentIndex = current.parent;
            current = vertices[current.parent-1];
         }
         backwardList[index] = currentIndex;
         
         for(int i = 0; i<pathLength; i++)
         {
            System.out.print(backwardList[i] + " --> ");
         }
         System.out.println(backwardList[pathLength]);
      }
   }
}