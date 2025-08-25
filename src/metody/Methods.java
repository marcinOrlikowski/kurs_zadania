package metody;

public class Methods {
    public static void main(String[] args) {
        //task 18
        System.out.println(gcdRecursive(21, 49));
    }

    private static int gcdRecursive(int a, int b) {
        if (a == b) {
            return a;
        } else if (a > b){
            return gcdRecursive(a-b,b);
        } else {
            return gcdRecursive(a,b-a);
        }
    }

}

