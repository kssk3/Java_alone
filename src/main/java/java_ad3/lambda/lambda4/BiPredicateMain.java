package java_ad3.lambda.lambda4;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class BiPredicateMain {

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> biPredicate = (s, n) -> s > n;
        System.out.println("biPredicate.test(1, 2) = " + biPredicate.test(1, 2));

        BiConsumer<Integer, String> biConsumer = (s , n) -> {
            for(int i = 0; i < s; i++){
                System.out.print(n + " ");
            }
            System.out.println();
        };
        biConsumer.accept(8, "*");

    }
}
