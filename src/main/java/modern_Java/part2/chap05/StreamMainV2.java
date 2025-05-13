package modern_Java.part2.chap05;

import java.util.stream.Stream;

public class StreamMainV2 {

    public static void main(String[] args) {

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

//        0.7579631740758049
//        0.012014378912227786
//        0.6307140108638752
//        0.8074415490570976
//        0.522283568951757
    }
}
