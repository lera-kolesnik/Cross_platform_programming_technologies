package tasks;
import java.util.HashSet;
import java.util.Set;

public class task3 {
public static int solutions(int a, int b, int c) {
    double D = b * b - 4 * a * c;
    if(D < 0) return 0;
    if(D == 0) return 1;
    return 2;
  }

  public static int findZip(String str) {
    if(str.indexOf("zip") == str.lastIndexOf("zip")) return -1;

    int firstInc = str.indexOf("zip");

    return str.indexOf("zip", firstInc + 1);
  }

  public static boolean checkPerfect(int num) {
    int sum = 1;
    int i = 2;
    while(i < num) {
      if(num % i == 0) {
        sum += i;
      }
      i++;
    }
    return sum == num;
  }

  public static String flipEndChars(String str) {

    int len = str.length();

    if(len < 2) return "Incompatible";
    if(str.charAt(0) == str.charAt(len - 1)) return "Two's a pair";

    return (str.charAt(len - 1) + str.substring(1, len - 1 ) + str.charAt(0));
  }

  public static boolean isValidHexCode(String hex) {
    if(hex.length() != 7) return false;
    if(hex.charAt(0) != '#') return false;
    hex = hex.toUpperCase();
    for(int i = 1; i < 7; i++) {
      if(
        (hex.charAt(i) < 48 || hex.charAt(i) > 57) &&
        (hex.charAt(i) < 65 || hex.charAt(i) > 70)
        ){
        return false;
      }
    }
    return true;
  }

  public static boolean same(int[] arr1, int[] arr2) {
    Set<Integer> a = new HashSet<Integer>(); 
    for(int i = 0; i < arr1.length; i++) {
      a.add(arr1[i]);
    }

    Set<Integer> b = new HashSet<Integer>(); 
    for(int i = 0; i < arr2.length; i++) {
      b.add(arr2[i]);
    }

    return a.size() == b.size();
  }

  public static boolean isKaprekar(int n) {
    if(n < 2) return true;

    String res = Integer.toString(n * n);

    String left = res.substring(0, res.length() / 2);
    String right = res.substring(res.length() / 2);
  
    int leftIn = Integer.parseInt(left);
    int rightIn = Integer.parseInt(right);

    return (leftIn + rightIn == n);
  }

  public static String longestZero(String str) {
    String result = "";
    int max = 0;

    for (String retval : str.split("1")) {
      if(retval.length() > max) {
        max = retval.length();
        result = retval;
      }
    }
    return result;
  }

  public static boolean isPrime(int num) {
    if (num % 2 == 0 && num > 2) {
      return false;
    }

    for (int i = 3; i <= Math.floor(Math.sqrt(num)); i++) {
      if (num % i == 0) {
        return false;
      }
    }

    return true;
  }

  public static int nextPrime(int num) {
    while(!isPrime(num)){
      num++;
    }
    return num;
  }

  public static boolean rightTriangle(int a, int b, int c) {

    if(a > b && a > c) {
      return (a * a == b * b + c * c);
    }
    if(b > c && b > a) {
      return (b * b == a * a + c * c);
    }
    if(c > a && c > b) {
      return (c * c == b * b + a * a);
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(solutions(1, 0, -1));
    System.out.println(solutions(1, 0, 0));
    System.out.println(solutions(1, 0, 1));

    System.out.println(findZip("all zip files are zipped zip"));
    System.out.println(findZip("all zip files are"));

    System.out.println(checkPerfect(6));
    System.out.println(checkPerfect(28));
    System.out.println(checkPerfect(496));
    System.out.println(checkPerfect(12));
    System.out.println(checkPerfect(97));

    System.out.println(flipEndChars("CAT"));
    System.out.println(flipEndChars("C"));
    System.out.println(flipEndChars("CAC"));

    System.out.println(isValidHexCode("#afa333"));
    System.out.println(isValidHexCode("#affa333"));
    System.out.println(isValidHexCode("afa333"));
    System.out.println(isValidHexCode("#afa3l3"));

    int[] nums1 = new int[] { 1, 3, 4, 4, 4 };
    int[] nums2 = new int[] { 2, 5, 7 };

    System.out.println(same(nums1, nums2));

    System.out.println(isKaprekar(5));
    System.out.println(isKaprekar(297));

    System.out.println(longestZero("01100001010001"));
    System.out.println(longestZero("1111"));
    System.out.println(longestZero("010011"));

    System.out.println(nextPrime(12));
    System.out.println(nextPrime(24));
    System.out.println(nextPrime(11));

    System.out.println(rightTriangle(3, 4, 5));
    System.out.println(rightTriangle(145, 105, 100));
    System.out.println(rightTriangle(70, 130, 110));
  }
}