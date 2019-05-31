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
         System.out.print(i+1 + ": ");
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
   // returns topologically sorted vertices
   public static int[] topSort()
   {
      LinkedList<Integer> queue = new LinkedList<Integer>();
      int n = graph.length;
      int[] in = indegrees();
      int[] A = new int[n];
      for(int i = 0; i<n; i++)
      {
         if(in[i] == 0)
            queue.add(i);
      }
      int i = 0;
      while(queue.size() != 0)
      {
         int temp = queue.remove();
         A[i] = temp;
         i++;
         for(int j = 0; j<graph[temp].size(); j++)
         {
            in[j] = in[j]-1;
            if(in[j] == 0)
               queue.add(j);
         }
      }
      if(i == n+1)
         throw new ArithmeticException("Graph is cyclical!");
      return A;
   }
   public static int[] topSort2()
   {
      int N = graph.length;
      int[] in = indegrees();
      int[] A = new int[N];
      LinkedList<Integer> queue = new LinkedList<Integer>();

      System.out.println("N:" + N);
      for(int u = 0; u<N; u++)
      {
         if(in[u] == 0)
            queue.offer(u);
      }

      int i = 0;
      System.out.println("queue size: " + queue.size());
      while(!queue.isEmpty())
      {
         int u = queue.remove();
         A[i] = u;
         i++;
         System.out.print(u + ": ");
         for(int v = 0; v<graph[u].size(); v++)
         {
            System.out.print(+graph[u].get(v)-1 + " ");
            in[graph[u].get(v)-1]--;
            //in[v] = in[v]-1;
            if(in[graph[u].get(v)-1] == 0)
               queue.offer(v);
         }
         System.out.println();
      }

      System.out.println("N length: " + N);
      System.out.println("i: " + i);
      //if(i != N+1)
      //   throw new ArithmeticException("Graph is cyclical!");

      return A;
   }
}