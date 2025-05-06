package java_ad3.stream.collectors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collectors3Grop {

    public static void main(String[] args) {
        // 첫 글자 알파벳을 기준으로 그룹화
        Stream<String> stream = List.of("Apple", "Avocado", "Banana", "BlueBerry", "Cherry").stream();
        Map<String, List<String>> grouped = stream.collect(Collectors.groupingBy(n -> n.substring(0, 1)));
        System.out.println("grouped = " + grouped);

        // 짝수(even)인지 여부로 분할 (파티셔닝)
        Map<Boolean, List<Integer>> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("numbers = " + numbers);
    }
}
