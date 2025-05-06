package java_ad3.lambda.ex3;

import java.util.List;
import java.util.function.BinaryOperator;

public class ReduceExample {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4);
        System.out.println("리스트 : " + list);

        int sum = reduce(list, 0, (a, b) -> a + b);
        System.out.println("합 (누적 +) : " + sum);

        int mul = reduce(list, 1, (a, b) -> a * b);
        System.out.println("합 (누적 *) : " + mul);
    }

    static int reduce(List<Integer> list, int initial, BinaryOperator<Integer> re) {
        for (Integer i : list) {
            initial = re.apply(i, initial);
        }
        return initial;
    }
}
