
package java_ad3.parallel;

import static java_ad3.Util.MyLogger.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class parallelMain5 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3");

        // 요청 풀 추가
        ExecutorService requestPool = Executors.newFixedThreadPool(100);

        int nThreads = 3;
        for(int i = 1; i <= nThreads; i++){
            String request = "request " + i;
            requestPool.submit(() -> logic(request));
            Thread.sleep(100);
        }
        requestPool.close();
    }

    private static void logic(String requestName) {
        log("[" + requestName + "] Start");
        long start = System.currentTimeMillis();
        int sum = IntStream.rangeClosed(1, 4)
                .parallel()
                .map(n -> HeavyJob.heavyJob(n, requestName))
                .reduce(0, (a, b) -> a + b);
        long end = System.currentTimeMillis();
        log("[" + requestName + "] Time " + (end - start) + "ms, sum = " + sum);
    }
}
