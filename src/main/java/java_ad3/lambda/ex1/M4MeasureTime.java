package java_ad3.lambda.ex1;

import java.util.Arrays;
import java_ad3.lambda.Procedure;

public class M4MeasureTime {
    public static void main(String[] args) {
        measure(() -> {
            int n = 100;
            int sum = 0;
            for(int i = 1; i <= n; i++) {sum += i;}
            System.out.println("[1부터 " + n + "까지의 합] 결과 = " + sum);
        });

        measure(() -> {
            int[] arr = {4, 3, 2, 1};
            System.out.println("원본 배열 : " + Arrays.toString(arr));
            Arrays.sort(arr);
            System.out.println("배열 정리 : " + Arrays.toString(arr));
        });
    }

    static void measure(Procedure procedure){
        long startNs = System.nanoTime();
        procedure.run();
        long endNs = System.nanoTime();
        System.out.println("실행 시간 : " + (endNs - startNs) +"\n");
    }
}
