package modern_Java.part2.chap07;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class MainTestV1 {
    public static void main(String[] args) {
        System.out.println("forkJoinSum() = " + forkJoinSum(10));

        int sum1 = IntStream.rangeClosed(1, 10).sum();
        System.out.println("sum1 = " + sum1);

        long sum2 = LongStream.rangeClosed(1, 10).sum();
        System.out.println("sum2 = " + sum2);
    }

    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1L, n).toArray();
        ForkJoinSumCalculatorV1 task = new ForkJoinSumCalculatorV1(numbers);
        return new ForkJoinPool().commonPool().invoke(task);
    }
}
