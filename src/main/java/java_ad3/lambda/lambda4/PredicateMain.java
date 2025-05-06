package java_ad3.lambda.lambda4;

import java.util.function.Predicate;

public class PredicateMain {

    public static void main(String[] args) {

        Predicate<Integer> predicate1 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 2;
            }
        };
        System.out.println("predicate1.test() = " + predicate1.test(1)); // predicate1.test() = false

        Predicate<Integer> predicate2 = n -> n / 2 == 1;
        System.out.println("predicate2.test() = " + predicate2.test(3)); // predicate2.test() = true

    }
}
