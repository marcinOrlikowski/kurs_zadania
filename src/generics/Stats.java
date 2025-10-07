package generics;

import java.util.Arrays;
import java.util.Objects;

public class Stats<T extends Number> {
    private final T[] data;

    public Stats(T[] data) {
        Objects.requireNonNull(data, "Array cannot be null");
        if (Arrays.stream(data).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Provided Array has empty elements");
        }
        if (data.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        this.data = data;
    }

    public double mean() {
        double sum = 0;
        for (T t : data) {
            sum += t.doubleValue();
        }
        return sum / data.length;
    }

    public double variance() {
        double mean = mean();
        double sumOfSquareDifferenceFromMean = 0;
        for (T t : data) {
            double DifferenceFromMean = t.doubleValue() - mean;
            double squareDifferenceFromMean = Math.pow(DifferenceFromMean, 2);
            sumOfSquareDifferenceFromMean += squareDifferenceFromMean;
        }
        return sumOfSquareDifferenceFromMean / data.length;
    }

    public boolean hasSameMean(Stats<?> other, double eps) {
        return (this.mean() - other.mean() <= eps);
    }
}
