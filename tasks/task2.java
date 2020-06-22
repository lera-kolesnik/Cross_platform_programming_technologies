package tasks;

public class task2{
public static String repeat(String str, int count) {
    String result = "";
    for(int i = 0; i <str.length(); i++) {
      for(int j = 0; j < count; j++) {
        result += str.charAt(i);
      }
    }
    return result;
  }

  public static int differenceMaxMin(int[] array) {
    int min = array[0];
    int max = array[0]; 
    for(int i = 1; i < array.length; i++) {
      if (min > array[i]) {
        min = array[i];
      }
      if (max < array[i]) {
        max = array[i];
      }
    }
    return max - min;
  }

  public static boolean isAvgWHole(int[] array) {
    int sum = 0;
    for(int i = 0; i < array.length ; i++) {
      sum+=array[i];
    }
    return sum % array.length == 0;
  }

  public static int[] cumulativeSum(int[] array) {
    int acc = 0;
    int[] resultArray = new int[array.length];
    for(int i = 0; i < array.length; i++) {
      acc += array[i];
      resultArray[i] = acc;
    }
    return array;
  }

  public static int getDecimapPlaces(String str) {
    int count = 0;
    boolean flag = false;

    for(int i = 0; i < str.length(); i++) {
      if(str.charAt(i) == '.') {
        flag = true;
        continue;
      }
      if(flag == true) {
        count++;
      }
    }

    return count;
  }
  
  public static int Fibonaci(int num) {
    int[] fib = new int[num];
    fib[0] = 1;
    fib[1] = 2;
    for(int i = 2; i < num; i++) {
      fib[i] = fib[i - 1] + fib[i - 2];
    }
    return fib[num - 1];
  }

  public static boolean isValid(String str) {
    if (str.length() != 5) return false;
    for(int i = 0; i < str.length(); i++) {
      if(str.charAt(i) < 48 || str.charAt(i) > 57) {
        return false;
      }
    }
    return true;
  }

  public static boolean isStrangePair(String str1, String str2) {
    return (
      str1.charAt(0) == str2.charAt(str2.length() - 1) &&
      str2.charAt(0) == str1.charAt(str1.length() - 1)
    );
  }

  public static boolean isPrefix(String word, String prefix) {
    return word.startsWith(prefix.substring(0, prefix.length() - 1));
  }

  public static boolean isSufix(String word, String sufix) {
    return word.endsWith(sufix.substring(1));
  }

  public static int boxSeq(int num) {
    int result = 0;
    for (int i = 1; i <= num; i++) {
      if(i % 2 == 0) {
        result -= 1;
      } else {
        result += 3;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(repeat("mice", 5));
    int[] nums = new int[] { 10, 4, 1, 4, -10, -50, 32, 21 };
    System.out.println(differenceMaxMin(nums));
    System.out.println(getDecimapPlaces("12421.2414"));
    System.out.println(Fibonaci(12));
    System.out.println(isValid("59001"));
    System.out.println(isStrangePair("ratio", "oretor"));
    System.out.println(isPrefix("automation", "auto-"));
    System.out.println(isSufix("automation", "-tion"));
    System.out.println(boxSeq(6));  
  } 
}