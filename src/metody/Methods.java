package metody;

public class Methods {
    public static void main(String[] args) {
        //task 15
        System.out.println(sumDigitsIterative(12345));
    }

    private static int sumDigitsIterative(int n) {
        int sum = 0;
        while (n != 0){
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

}

