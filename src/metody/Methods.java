package metody;

public class Methods {
    public static void main(String[] args) {
        //task 17
        System.out.println(gcdIterative(21, 49));
    }

    private static int gcdIterative(int a, int b) {
        while (a != b) {
            if (a > b){
                a -= b;
            } else
                b -= a;
        }
        return a;
    }

}

