package modern_Java.part2.chap05;

import java.util.Arrays;
import java.util.List;

public class StreamFlatMapEx1 {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> result1 = numbers.stream()
                .map(n -> n * n)
                .toList();
        System.out.println("result1 = " + result1);
        // result1 = [1, 4, 9, 16, 25]

        List<Integer> numbers1 = List.of(1, 2, 3);
        List<Integer> numbers2 = List.of(4, 5);
        List<List<Integer>> result2 = numbers1.stream()
                .flatMap(n -> numbers2.stream().map(
                        m -> List.of(n, m)
                )).toList();
        System.out.println("result2 = " + result2);
        // result2 = [[1, 4], [1, 5], [2, 4], [2, 5], [3, 4], [3, 5]]

    }
}
