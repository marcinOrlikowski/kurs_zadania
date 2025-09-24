package exceptions;

class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
