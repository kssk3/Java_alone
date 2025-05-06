package java_ad3.lambda.ex1;

import java.util.Arrays;
import java_ad3.lambda.Procedure;

public class M3MeasureTime {
    public static void main(String[] args) {
        measure(new Procedure() {
            @Override
            public void run() {
                int max = 100;
                int sum = 0;
                for (int i = 1; i <= max; i++) {
                    sum += i;
                }
                System.out.println("[1부터 " +  max + "까지 합] 결과 : " + sum);
            }
        });

        measure(new Procedure() {
            @Override
            public void run() {
                int[] arr = {4, 3, 2, 1};
                System.out.println("원본 배열: " + Arrays.toString(arr));
                Arrays.sort(arr);
                System.out.println("배열 정렬: " + Arrays.toString(arr));
            }
        });
    }

    static void measure(Procedure procedure) {
        long startNs = System.nanoTime();
        procedure.run(); // 바뀌는 로직 실행 (익명 클래스 or 람다로 전달)
        long endNs = System.nanoTime();
        System.out.println("실행 시간 : " + (endNs - startNs) +"\n");
    }


}
