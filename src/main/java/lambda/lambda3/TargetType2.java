package lambda.lambda3;

import java.util.function.Function;

public class TargetType2 {

    public static void main(String[] args) {
        Function<String, String> upperCase = s -> s.toUpperCase();
        String result1 = upperCase.apply("function_interface");
        System.out.println("result1 = " + result1);

        Function<Integer, Integer> squaer = n -> n * n;
        int result2 = squaer.apply(10);
        System.out.println("result2 = " + result2);
    }
}
