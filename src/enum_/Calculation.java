package enum_;

public enum Calculation {
    ADD {
        @Override
        public int calculate(int a, int b) {
            return a + b;
        }
    }, SUBTRACT {
        @Override
        public int calculate(int a, int b) {
            return a - b;
        }
    }, MULTIPLY {
        @Override
        public int calculate(int a, int b) {
            return a * b;
        }
    }, DIVIDE {
        @Override
        public int calculate(int a, int b) {
            return a / b;
        }
    };

    public abstract int calculate(int a, int b);
}
