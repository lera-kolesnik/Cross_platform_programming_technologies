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
        System.out.printf("%s - %s\n", "level", isPalindrome("level") );
        System.out.printf("%s - %s\n", "eye", isPalindrome("eye") );
        System.out.printf("%s - %s\n", "word", isPalindrome("word") );
}
}