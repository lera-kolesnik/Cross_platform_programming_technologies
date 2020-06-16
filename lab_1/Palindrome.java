package lab_1;

public class Palindrome {
    public static boolean isPalindrome(String str){
        for(short i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if(isPalindrome(args[i])) {
              System.out.printf("%s - %s\n", args[i], "палиндром");
            } 
            else {
              System.out.printf("%s - %s\n", args[i], "не палиндром");
            }
        }
    }
}