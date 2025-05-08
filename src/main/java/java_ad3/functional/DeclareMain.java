package java_ad3.functional;

import java.util.ArrayList;
import java.util.List;

public class DeclareMain {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result1 = new ArrayList<>();

        for (Integer number : numbers) {
            if(number % 2 == 0) {
                result1.add(number * number);
            }
        }
        System.out.println("result1 = " + result1);

        List<Integer> result2 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .toList();

        System.out.println("result2 = " + result2);
    }
}
