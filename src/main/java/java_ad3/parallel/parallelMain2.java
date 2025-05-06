package java_ad3.parallel;

import static java_ad3.Util.MyLogger.log;

public class parallelMain2 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        SumTask task1 = new SumTask(1, 4);
        SumTask task2 = new SumTask(5, 8);

        Thread threadA = new Thread(task1, "Thread1");
        Thread threadB = new Thread(task2, "Thread2");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
        System.out.println("main 스레드 대기 완료");

        int sum = task1.result + task2.result;
        long end = System.currentTimeMillis();

        log("time: " + (end - start) + "ms, sum: " + sum);
    }


    static class SumTask implements Runnable {
        @Override
        public void run() {
            log("작업 시작");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyJob(i);
                sum += calculated;
            }
            result = sum;
            log("작업 완료 result = " + result);
        }

        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }
    }
}
