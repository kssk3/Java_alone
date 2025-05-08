package java_ad3.functional;

import java.util.function.Function;

public class CompositionMain2 {

    public static void main(String[] args) {

//        Function<String, Integer> parseInt = s -> Integer.parseInt(s);
        Function<String, Integer> parseInt = Integer::parseInt;

        Function<Integer, Integer> square = x -> x * x;

        Function<Integer, String> toString = x -> "결과 : " + x;

        Function<String, String> finalFunc = parseInt.
                andThen(square).
                andThen(toString);

        System.out.println("finalFunc.apply(\"5\") = " + finalFunc.apply("5"));

    }
}
