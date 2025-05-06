
package java_ad3.parallel;

import static java_ad3.Util.MyLogger.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class parallelMain6 {
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

        Future<Integer> f1 = es.submit(() -> HeavyJob.heavyJob(1, requestName));
        Future<Integer> f2 = es.submit(() -> HeavyJob.heavyJob(2, requestName));
        Future<Integer> f3 = es.submit(() -> HeavyJob.heavyJob(3, requestName));
        Future<Integer> f4 = es.submit(() -> HeavyJob.heavyJob(4, requestName));
        int sum;
        try {
            Integer i1 = f1.get();
            Integer i2 = f2.get();
            Integer i3 = f3.get();
            Integer i4 = f4.get();
            sum = i1 + i2 + i3 + i4;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        log("[" + requestName + "] Time " + (end - start) + "ms, sum = " + sum);
        return sum;
    }
}
