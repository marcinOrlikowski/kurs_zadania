package multithreading;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        createAndRunCountingThreads(5);
        sumArrayByMultiThreading(4);
        executorService(4);
    }

    private static void executorService(int threadNumbers) {
        ExecutorService pool = Executors.newFixedThreadPool(threadNumbers);
        List<Integer> list = createList();
        List<Future<BigInteger>> futures = new ArrayList<>();

        for (Integer i : list) {
            futures.add(pool.submit(new FactorialTask(i)));
        }

        int n = 1;
        System.out.println("\nn -> factorial(n)");
        for (Future<BigInteger> future : futures) {
            try {
                System.out.println(n++ + " -> " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Could not get future" + e.getMessage());
            }
        }
        pool.shutdown();
    }

    private static List<Integer> createList() {
        return IntStream.rangeClosed(1, 20)
                .boxed()
                .toList();
    }

    private static void sumArrayByMultiThreading(int threadNumbers) {
        int[] array = createAndFillArray();
        int[][] arrayInParts = splitArray(array, threadNumbers);
        Thread[] threadArray = new Thread[threadNumbers];
        AtomicLong multiThreadedSum = new AtomicLong(0);

        for (int i = 0; i < threadNumbers; i++) {
            int currentPart = i;
            threadArray[i] = new Thread(() -> multiThreadedSum.addAndGet(Arrays.stream(arrayInParts[currentPart]).sum()));
            threadArray[i].start();
        }
        for (Thread thread : threadArray) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Could not wait for thread " + e.getMessage());
            }
        }

        System.out.println("Multithreaded sum: " + multiThreadedSum);
        System.out.println("SingleThreaded sum: " + Arrays.stream(array).sum());
    }

    private static int[][] splitArray(int[] sourceArray, int partsNumber) {
        float bucketSize = sourceArray.length / (float) partsNumber;
        int[][] result = new int[partsNumber][];

        for (int currentBucket = 0; currentBucket < partsNumber; currentBucket++) {
            int from = (int) Math.ceil(currentBucket * bucketSize);
            int to = (int) Math.ceil(currentBucket * bucketSize + bucketSize);
            result[currentBucket] = Arrays.copyOfRange(sourceArray, from, to);
        }
        return result;
    }

    private static int[] createAndFillArray() {
        int[] array = new int[1000000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }

    private static void createAndRunCountingThreads(int threadNumbers) {
        Thread[] threadArray = new Thread[threadNumbers];

        for (int i = 0; i < threadNumbers; i++) {
            threadArray[i] = new Thread(new CountingThread(i));
            threadArray[i].start();
        }
        for (Thread thread : threadArray) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Could not wait for thread " + e.getMessage());
            }
        }
        System.out.println("All threads finished their work\n");
    }
}
