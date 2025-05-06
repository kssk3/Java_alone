package java_ad3.parallel.forkJoin;

import static java_ad3.Util.MyLogger.*;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java_ad3.parallel.HeavyJob;

public class SumTask extends RecursiveTask<Integer> {
    @Override
    protected Integer compute() {
        if(list.size() <= THRESHOLD){
            log("[작업 처리]" + list);
            int sum = list.stream()
                    .mapToInt(HeavyJob::heavyJob)
                    .sum();
            log("[처리 완료] " + list + " -> sum = " + sum);
            return sum;
        }else{
            int mid = list.size() / 2;
            List<Integer> leftList = list.subList(0, mid);
            List<Integer> RightList = list.subList(mid, list.size());

            log("[분할] " + list + " -> LEFT " + leftList + " -> RIGHT = " + RightList);

            SumTask leftTask = new SumTask(leftList);
            SumTask RightTask = new SumTask(RightList);

            // 왼쪽 작업은 다른 스레드에서 처리
            leftTask.fork();
            // 오른쪽 작업은 현재 스레드에서 처리
            Integer rightResult = RightTask.compute();

            // 왼쪽 작업 결과를 기다림
            Integer leftResult = leftTask.join();

            // 왼쪽과 오른쪽 작업 결과를 합침
            int joinResult = rightResult + leftResult;
            log("LEFT [" + leftResult + "] -> RIGHT [" + rightResult + "] -> " + joinResult);
            return joinResult;
        }
    }

    private static final int THRESHOLD = 2;

    private List<Integer> list;

    public SumTask(List<Integer> list) {
        this.list = list;
    }
}
