package modern_Java.part2.chap07;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculatorV1 extends RecursiveTask<Long> {

    public static final int THRESHOLD = 4;

    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculatorV1(long[] numbers) {
        this(numbers, 0, numbers.length - 1);
    }

    private ForkJoinSumCalculatorV1(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int len = end - start;

        if (len <= THRESHOLD) {
            return computeSequentially();
        }

        int mid = (start + end) / 2;

        ForkJoinSumCalculatorV1 leftSide = new ForkJoinSumCalculatorV1(numbers, start, mid);
        ForkJoinSumCalculatorV1 rightSide = new ForkJoinSumCalculatorV1(numbers, mid + 1, end);

        leftSide.fork();
        Long rightResult = rightSide.compute();
        Long leftResult = leftSide.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for(int i = start; i <= end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

}
