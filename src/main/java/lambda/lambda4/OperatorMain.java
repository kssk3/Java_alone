package lambda.lambda4;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class OperatorMain {

    public static void main(String[] args) {
        // UnaryOperator
        Function<Integer, Integer> square1 = n -> n * n;
        System.out.println("square1.apply(5) = " + square1.apply(5));

        UnaryOperator<Integer> square2 = n -> n * n;
        System.out.println("square2.apply(5) = " + square2.apply(5) + "\n");

        // BinaryOperator
        BiFunction<Integer, Integer, Integer> addiction1 = (n1, n2) -> n1 * n2;
        System.out.println("addiction1.apply(5, 5) = " + addiction1.apply(5, 5));

        BinaryOperator<Integer> addiction2 = (n1, n2) -> n1 * n2;
        System.out.println("addiction2.apply(5, 5) = " + addiction2.apply(5, 5));
    }
}
