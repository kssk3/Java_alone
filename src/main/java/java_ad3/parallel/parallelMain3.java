package java_ad3.parallel;

import static java_ad3.Util.MyLogger.log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class parallelMain3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int processors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        log("processors: " + processors + ", commonPool: " + commonPool.getParallelism());

        long start = System.currentTimeMillis();
        int sum = IntStream.rangeClosed(1, 8)
                .parallel()
                .map(HeavyJob::heavyJob)
                .reduce(0, Integer::sum);
        long end = System.currentTimeMillis();

        log("time: " + (end - start) + ", sum = " + sum);
    }


    static class SumTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log("작업 시작");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyJob(i);
                sum += calculated;
            }
            log("작업 완료 result = " + sum);
            return sum;
        }

        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

    }
}
