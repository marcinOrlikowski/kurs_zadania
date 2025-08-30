package metody;

public class Methods {
    public static void main(String[] args) {
        //task 12
        System.out.println(isPrime(21) ? "Prime" : "Not prime");
    }

    private static boolean isPrime(int n) {
        boolean dividedByMoreThanTwoFactors = false;
        if (n <= 1){
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0){
                dividedByMoreThanTwoFactors = true;
            }
        }
        return !dividedByMoreThanTwoFactors;
    }

}

