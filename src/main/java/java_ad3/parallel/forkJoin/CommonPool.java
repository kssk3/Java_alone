package java_ad3.parallel.forkJoin;

import static java_ad3.Util.MyLogger.log;

import java.util.List;
import java.util.stream.IntStream;

public class CommonPool {

    public static void main(String[] args) {
        int processCount = Runtime.getRuntime().availableProcessors();
//        ForkJoinPool commonPool = ForkJoinPool.commonPool(); // 프로그램이 시작 시 자동적으로 공용 풀을 설정해서 코드를 작성하지 않아도 됨
//        log("processCount: " + processCount + ", commonPool: " + commonPool.getParallelism());

        List<Integer> data = IntStream.rangeClosed(1, 8)
                .boxed()
                .toList();

        long start = System.currentTimeMillis();
        log("[생성] " + data);

        SumTask sumTask = new SumTask(data);
        Integer result = sumTask.invoke(); // 공용 풀 사용

        log("[최종 결과] " + result);
        long end = System.currentTimeMillis();

        log("time: " + (end - start) + "ms, sum = " + result);
    }
}
