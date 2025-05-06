package java_ad3.parallel;

import static java_ad3.Util.MyLogger.*;

import java.util.stream.IntStream;

public class parallelMain1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int sum = IntStream.rangeClosed(1, 8)
                .map(HeavyJob::heavyJob)
                .reduce(0, (a, b) -> a + b);
        long end = System.currentTimeMillis();

        log("time: " + (end - start) + "ms, sum: " + sum);
    }
}
