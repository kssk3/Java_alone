
package java_ad3.parallel;

import static java_ad3.Util.MyLogger.log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class parallelMain4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        long start = System.currentTimeMillis();

        SumTask task1 = new SumTask(1, 4);
        SumTask task2 = new SumTask(5, 8);

        Future<Integer> future1 = es.submit(task1);
        Future<Integer> future2 = es.submit(task2);

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        int sum = result1 + result2;
        long end = System.currentTimeMillis();
        log("time: " + (end - start) + "ms, sum: " + sum);

        es.close();
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
