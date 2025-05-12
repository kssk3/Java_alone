package modern_Java.part2.chap05;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamFlatMapV1 {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("Hello", "World");

        List<String[]> result1 = words.stream()
                .map(n -> n.split(""))
                .distinct()
                .toList();

        System.out.println("result1 = " + result1);
        // result1 = [[Ljava.lang.String;@63961c42, [Ljava.lang.String;@65b54208]

        List<String> result2 = words.stream()
                .map(n -> n.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList();

        System.out.println("result2 = " + result2);
        // result3 = [H, e, l, o, W, r, d]

    }
}
