package java_ad3.parallel.forkJoin;

import static java_ad3.Util.MyLogger.log;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ForkJoinMain1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = IntStream.rangeClosed(1, 8)
                .boxed()
                .toList();

        log("[생성] " + list);

        ForkJoinPool pool = new ForkJoinPool(10);

        long start = System.currentTimeMillis();
        SumTask sumTask = new SumTask(list);
        Integer result = pool.invoke(sumTask);
//        ForkJoinTask<Integer> submit = pool.submit(sumTask);
//        Integer result = submit.get();
        pool.close();
        long end = System.currentTimeMillis();

        log("time: " + (end - start) + "ms, sum = " + result);
        log("pool " + pool);

    }
}
