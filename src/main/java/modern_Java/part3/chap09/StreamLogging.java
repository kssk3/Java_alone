package modern_Java.part3.chap09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamLogging {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2,3,4,5,6,7,8);
        numbers.stream()
                .peek(n -> System.out.println("from stream : " + n))
                .map(n -> n + 17)
                .peek(n -> System.out.println("after map : " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("after filter : " + n))
                .limit(3)
                .peek(n -> System.out.println("after limit : " + n))
                .forEach(System.out::println);
    }
}
