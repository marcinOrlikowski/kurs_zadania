package metody;

public class Methods {
    public static void main(String[] args) {
        //task 3
        int result = max(9, 3);
        System.out.println(result);
    }

    private static int max(int a, int b) {
        if (a > b){
            return a;
        } else {
            return b;
        }
    }

}

