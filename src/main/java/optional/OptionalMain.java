package optional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalMain {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Optional<List<Integer>> optionalNumbers = Optional.of(numbers);

        List<Integer> list = optionalNumbers.stream().
                flatMap(List::stream).
                filter(n -> n > 2).toList();
        System.out.println("list = " + list);


    }
}
