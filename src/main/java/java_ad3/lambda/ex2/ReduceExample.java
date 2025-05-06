package java_ad3.lambda.ex2;

import java.util.List;

public class ReduceExample {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4);
        System.out.println("리스트 : " + list);

        int sum = reduce(list, 0, (a, b) -> a + b);
//        int sum = reduce(list, 0, new MyReducer() {
//            @Override
//            public int reduce(int a, int b) {
//                return a + b;
//            }
//        });
        System.out.println("합 (누적 +) : " + sum);

        int mul = reduce(list, 1, (a, b) -> a * b);
        System.out.println("합 (누적 *) : " + mul);
    }

    static int reduce(List<Integer> list, int initial, MyReducer reducer) {
        for (Integer i : list) {
            initial = reducer.reduce(i, initial);
        }
        return initial;
    }
}
