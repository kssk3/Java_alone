package lambda.ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterExampleEx1 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-3, -2, -1, 1, 2, 3, 5);
        System.out.println("원본 리스트 : " + numbers);

        List<Integer> negative = filter(numbers,new MyPredicate() {
            @Override
            public boolean test(int value) {
                return value < 0;
            }
        });
        System.out.println("음수만 정렬 : " + negative);

        List<Integer> odd = filter(numbers, new MyPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });
        System.out.println("짝수만 정렬 : " + odd);
    }

    static List<Integer> filter(List<Integer> list, MyPredicate predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            if (predicate.test(i)) {
                result.add(i);
            }
        }
        return result;
    }

}
