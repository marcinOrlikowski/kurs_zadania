package ffwork.exceptions;

public class InvalidCommandArgumentException extends RuntimeException {
    public InvalidCommandArgumentException(String message) {
        super(message);
    }
}
