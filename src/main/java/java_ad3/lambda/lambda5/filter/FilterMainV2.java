package java_ad3.lambda.lambda5.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterMainV2 {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 짝수만 거르기
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evenNumbers = filter(numbers, isEven);
        System.out.println("evenNumbers = " + evenNumbers);
        // 홀수만 거르기
        Predicate<Integer> isOdd = n -> n % 2 == 1;
        List<Integer> oddNumbers = filter(numbers, isOdd);
        System.out.println("oddNumbers = " + oddNumbers);
    }

    private static List<Integer> filter(List<Integer> number, Predicate<Integer> predicate) {
        List<Integer> filtered = new ArrayList<>();
        for (Integer n : number) {
            if (predicate.test(n)) {
                filtered.add(n);
            }
        }
        return filtered;
    }
}



