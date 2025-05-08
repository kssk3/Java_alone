package modern_Java.chap03.Predicate;

import java.util.List;
import java.util.function.Predicate;

public class PredicateMainV1 {

    public static void main(String[] args) {
        List<String> strings = List.of("one", "two", "three", "four", "five", "six");
        System.out.println("strings = " + strings);

        List<String> newStrings = strings.stream()
                .filter(n -> n.length() > 3)
                .toList();

        System.out.println("newStrings = " + newStrings);
    }
}
