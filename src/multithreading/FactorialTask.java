package multithreading;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class FactorialTask implements Callable<BigInteger> {
    int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger result = BigInteger.ONE;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            Thread.sleep(500);
            return result;
        } else {
            return BigInteger.ZERO;
        }
    }
}
