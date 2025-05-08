package Moden_Java.chap02.Behavior_Parameterization;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableMainV1 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();

//        Future<String> threadName = es.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return Thread.currentThread().getName();
//            }
//        });
        Future<String> threadName = es.submit(() -> Thread.currentThread().getName());
        System.out.println("threadName = " + threadName);

        // 코드가 끝나면 무조건 close() 닫아주기
        es.close();
    }
}
