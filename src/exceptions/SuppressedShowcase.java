package exceptions;

import java.util.Arrays;

final class SuppressedShowcase {
    static void run() {
        try (DummyResource r = new DummyResource()) {
            r.work();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getSuppressed()));
        }
    }
}
