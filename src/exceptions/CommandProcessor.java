package exceptions;

final class CommandProcessor {
    static void processCommand(String cmd) {
        // TODO: warunki i rzucanie wyjątków
        if (cmd.equalsIgnoreCase("ARG")) {
            throw new IllegalArgumentException("bad arg");
        }
        if (cmd.equalsIgnoreCase("STATE")) {
            throw new IllegalStateException("bad state");
        }
    }

    static void demo() {
        // TODO: wywołania + multi-catch
        try {
            processCommand("arg");
            processCommand("state");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}
