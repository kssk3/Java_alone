package modern_Java.part2.chap04.Stream.ex3;

import java.util.List;

public class StreamTestMainV1 {
    public static void main(String[] args) {

        List<String> title = List.of("java8", "In", "Action");

        title.stream()
                .forEach(System.out::println);
    }
}
