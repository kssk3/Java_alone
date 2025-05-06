
package java_ad3.parallel;

import static java_ad3.Util.MyLogger.log;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class parallelMain7 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService requestPool = Executors.newFixedThreadPool(100);
        // 내부에서 작업용 Executors
        ExecutorService es = Executors.newFixedThreadPool(400);

        int nThreads = 3;
        for(int i = 1; i <= nThreads; i++){
            String request = "request " + i;
            requestPool.submit(() -> logic(request, es));
            Thread.sleep(100);
        }
        requestPool.close();
        es.close();
    }

    private static int logic(String requestName, ExecutorService es) {
        log("[" + requestName + "] Start");
        long start = System.currentTimeMillis();

        List<Future<Integer>> futures = IntStream.rangeClosed(1, 4)
                .mapToObj(i -> es.submit(() -> HeavyJob.heavyJob(i, requestName)))
                .toList();

        int sum = futures.stream()
                .mapToInt(f -> {
                    try {
                        return f.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).sum();

        long end = System.currentTimeMillis();
        log("[" + requestName + "] Time " + (end - start) + "ms, sum = " + sum);
        return sum;
    }
}
