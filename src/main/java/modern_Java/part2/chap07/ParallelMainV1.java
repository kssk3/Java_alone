package modern_Java.part2.chap07;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelMainV1 {
    private static final long N = 1_000_000L;

    public static void main(String[] args) {
        ParallelMainV1 p = new ParallelMainV1();

        System.out.println("p.parallelSum(N) = " + p.sideEffectSum(N));
        System.out.println("p.sideEffectParallelSum(N) = " + p.sideEffectParallelSum(N));
     }

    public long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .forEach(accumulator::add);
        return accumulator.total;
    }

    public long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);
        return accumulator.total;
    }


    public class Accumulator {
        private long total = 0;
        private void add(long value) {total += value;}
    }
}
