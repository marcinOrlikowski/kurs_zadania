package exceptions;

final class DummyResource implements AutoCloseable {
    void work() {
        throw new RuntimeException("primary");
    }

    @Override
    public void close()
    {
        throw new RuntimeException("close");
    }
}
