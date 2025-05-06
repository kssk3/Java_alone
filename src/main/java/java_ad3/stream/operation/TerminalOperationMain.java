package java_ad3.stream.operation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TerminalOperationMain {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 5, 6, 7, 7, 8, 9, 10);

        // Collectors는 뒤에서 더 자세히 (복잡한 수집이 필요할 때 사용)
        System.out.println("1. collect - List 수집");
        List<Integer> evenNumbers1 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("짝수 리스트 = " + evenNumbers1);
        System.out.println();

        System.out.println("2. toList() - (Java 16+)");
        List<Integer> evenNumbers2 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        System.out.println("짝수 리스트 = " + evenNumbers2);
        System.out.println();

        System.out.println("3. toArray() -> 배열로 변환");
        Integer[] arr = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);
        System.out.println("짝수 배열 = " + Arrays.toString(arr));
        System.out.println();

        System.out.println("4. forEach() -> 각 요소를 처리");
        numbers.stream()
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        System.out.println("5. count() -> 요소 개수");
        long count = numbers.stream()
                .filter(n -> n > 5)
                .count();
        System.out.println("5보다 큰 숫자 개수  = " + count);
        System.out.println();

        System.out.println("6. reduce() -> 누적 합 ");
        Optional<Integer> sum1 = numbers.stream()
                .reduce(Integer::sum);
        System.out.println("초기 값이 없는 누적 값 = " + sum1.get());

        int sum2 = numbers.stream()
                .reduce(100, (Integer::sum));
        System.out.println("초기 값이 100 누적 값 = " + sum2);
        System.out.println();

        System.out.println("7. min() -> 최솟값");
        Optional<Integer> min = numbers.stream()
                .min(Integer::compareTo);
        System.out.println("최솟값 = " + min.get());
        System.out.println();

        System.out.println("8. max() -> 최댓값");
        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);
        System.out.println("최대값 = " + max.get());
        System.out.println();

        System.out.println("9. findFirst() -> 조건에 맞는 첫 번째 값");
        Integer first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst().get();
        System.out.println("5보다 이상인 첫 번째 값 = " + first);
        System.out.println();

        System.out.println("10. findAny() -> 아무 요소나 하나 찾기");
        // 일반 Stream()의 경우 순차적으로 찾는 '경향'이 있어 조건에 맞는 첫 번째 값을 불러온다.
        // 정말 조건에 맞는 요소가 필요할 경우 병령 스트림 parallel() 메서드화 한 후에 동작하면 아무 값이나 가져온다.
        Integer any = numbers.stream()
                .filter(n -> n > 5)
                .findAny().get();
        System.out.println("5보다 이상인 값 = " + any);
        System.out.println();

        System.out.println("11. anyMatch() -> 조건에 만족하는 값이 있는지 boolean 반환");
        boolean result1 = numbers.stream()
                .anyMatch(n -> n > 5);
        System.out.println("5보다 이상의 값이 있는지 : " + result1);
        System.out.println();

        System.out.println("12. allMatch() -> 조건에 모두 만족하는지 boolean 반환");
        boolean result2 = numbers.stream()
                .allMatch(n -> n > 5);
        System.out.println("값이 전부 5보다 큰 값인지? : : " + result2);
        System.out.println();

        System.out.println("13. () -> 조건에 만족하지 않는 값이 있는지 boolean 반환");
        boolean result3 = numbers.stream()
                .noneMatch(n -> n < 0);
        System.out.println("값이 전부다 양수인가? : : " + result3);
        System.out.println();
    }
}
