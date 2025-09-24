package exceptions;

final class NumberParser {
    static int parsePositiveInt(String s) {
        int parsed;
        try {
            parsed = Integer.parseInt(s);
            if (parsed > 0) {
                return parsed;
            } else
                throw new IllegalArgumentException("non-positive: " + parsed);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(String.format("Invalid number: <%s>", s), e.getCause());
        }
    }
}
