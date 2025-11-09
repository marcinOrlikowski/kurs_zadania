package multithreading;

public class CountingThread implements Runnable {
    private int threadNumber;

    public CountingThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " Written by thread: " + threadNumber);
            try {
                Thread.sleep(100);
            } catch (InterruptedException _) {
            }
        }
    }
}
