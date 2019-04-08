import java.lang.*;

class RunTimes{

   public static void runLinear(){
      Algorithms algorithm = new Algorithms();
      long start, end, N = 1000;
      System.out.println("Linear algorithm's running times:");
      while (N < 100000000){
         start = System.nanoTime();
         algorithm.linearAlgorithm(N);
         end = System.nanoTime();
         String output = String.format("T(%d) = %d", N, ((end - start) / 1000000));
         System.out.println(output);
         N = N * 2;
      }
      System.out.println();
   }

   public static void runLogarithmic(){
      Algorithms algorithm = new Algorithms();
      long start, end, N = 1000;
      System.out.println("Logarithmic algorithm's running times:");
      while (N < 100000000){
         start = System.nanoTime();
         algorithm.logarithmicAlgorithm(N);
         end = System.nanoTime();
         String output = String.format("T(%d) = %d", N, ((end - start) / 1000000));
         System.out.println(output);
         N = N * 2;
      }
      System.out.println();
   }

   public static void runNLogN(){
      Algorithms algorithm = new Algorithms();
      long start, end, N = 1000;
      System.out.println("NogN algorithm's running times:");
      while (N < 100000000){
         start = System.nanoTime();
         algorithm.NlogNAlgorithm(N);
         end = System.nanoTime();
         String output = String.format("T(%d) = %d", N, ((end - start) / 1000000));
         System.out.println(output);
         N = N * 2;
      }
      System.out.println();
   }

   public static void runQuadratic(){
      Algorithms algorithm = new Algorithms();
      long start, end, N = 1000;
      System.out.println("Quadratic algorithm's running times:");
      while (N <= 512000){
         start = System.nanoTime();
         algorithm.quadraticAlgorithm(N);
         end = System.nanoTime();
         String output = String.format("T(%d) = %d", N, ((end - start) / 1000000));
         System.out.println(output);
         N = N * 2;
      }
      System.out.println();
   }

   public static void runCubic(){
      Algorithms algorithm = new Algorithms();
      long start, end, N = 1000;
      System.out.println("Cubic algorithm's running times:");
      while (N <= 8000){
         start = System.nanoTime();
         algorithm.cubicAlgorithm(N);
         end = System.nanoTime();
         String output = String.format("T(%d) = %d", N, ((end - start) / 1000000));
         System.out.println(output);
         N = N * 2;
      }
      System.out.println();
   }

   public static void main(String[] args){
      runLogarithmic();
      runLinear();
      runNLogN();
      runQuadratic();
      runCubic();
   }
}
