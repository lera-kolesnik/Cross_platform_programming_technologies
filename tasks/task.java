package tasks;

public class task {
  
    public static int remainder(int a,int b) {
      return a % b;
    }
  
    public static double triArea(int a, int h) {
      return (0.5 * a * h);
    }
  
    public static int animals(int chic, int cows, int pig) {
      return (2 * chic + 4 * cows + 4 * pig);
    }
  
    public static boolean profitableGamble(double prob,double prize,double pay) {
      return (prob * prize > pay);
    }
  
    public static String operation(int a, int b, int N) {
      if(a + b == N) return "added";
      if(a - b == N) return "subtracted";
      if(a * b == N) return "multiple";
      if(a / b == N) return "divided";
      return "none";
    }
  
    public static int ctoa(char letter) {
      return (int) letter;
    }
  
    public static int appUpTo(int num) {
      int result = 0;
      for(int i = 1; i <= num ; i++) {
        result += i;
      }
      return result;
    }
  
    public static int nextEdge(int a, int b) {
      return a + b - 1;
    }
  
    public static int subOfCubes(int[] array) {
      int result = 0;
      for (int i = 0; i < array.length; i++){
        result += Math.pow(array[i], 3);
      }
      return result;
    }
  
    public static boolean abcmath(int a, int b, int c) {
      int res = a;
      for(int i = 0; i < b; i++) {
        res+=res;
      }
      System.out.println(res);
      return res % c == 0;
    }

    public static void main(String[] args) {
      System.out.println(abcmath(42, 5, 10));
    }
}
  