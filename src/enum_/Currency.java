package enum_;

public enum Currency {
    PLN("zł"), USD("$"), EUR("€");

    private String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
