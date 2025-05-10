package modern_Java.part2.chap05;

import java.util.List;

public class StreamSlicingMainV1 {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(150, 170, 210, 250, 310, 350);
        System.out.println("numbers = " + numbers);

        List<Integer> result = numbers.stream()
                .filter(n -> n > 170)
                .limit(3)
                .toList();

        System.out.println("result = " + result);
    }
}
// numbers = [150, 170, 210, 250, 310, 350]
// result = [210, 250, 310]

