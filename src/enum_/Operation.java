package enum_;

public enum Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public void calculate(int a, int b) {
        switch (this) {
            case ADD -> {
                System.out.println("a + b = " + (a + b));
            }
            case SUBTRACT -> {
                System.out.println("a - b = " + (a - b));
            }
            case MULTIPLY -> {
                System.out.println("a * b = " + (a * b));
            }
            case DIVIDE -> {
                System.out.println("a / b = " + (a / b));
            }
        }
    }
}
