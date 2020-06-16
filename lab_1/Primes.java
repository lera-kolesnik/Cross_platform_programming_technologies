package lab_1;
public class Primes {
    

    public static boolean IsPrime (int n){
        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0){
            return false;
        }

        for (int i = 3; i <= Math.floor(Math.sqrt(n)); i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 2; i < 100; i++ ){
            if(IsPrime(i)) {
                System.out.printf("%s ", i);
            }
        }
    }
}