package modern_Java.part2.chap05;


import java.util.List;
import java.util.stream.Stream;

public class StreamMainV1 {

    public static void main(String[] args) {

        List<Integer> result1 = Stream.iterate(1, n -> n + 2)
                .limit(5)
                .toList();
        System.out.println("result1 = " + result1);

        List<Integer> result2 = Stream.iterate(0, n -> n + 2)
                .limit(10)
                .toList();
        System.out.println("result2 = " + result2);

        List<Integer> result3 = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .toList();

        System.out.println("result3 = " + result3);

    }

    // result1 = [1, 3, 5, 7, 9]
    // result2 = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
    // 피보나치 수열 result3 = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
}
