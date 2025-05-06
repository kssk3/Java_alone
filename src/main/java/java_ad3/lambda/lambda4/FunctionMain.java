package java_ad3.lambda.lambda4;

import java.util.function.Function;

public class FunctionMain {
    public static void main(String[] args) {

        Function<String, Integer> function1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println("function1.apply() = " + function1.apply("안녕하세요."));

        Function<String, Integer> function2 = s -> s.length();
        System.out.println("function2.apply() = " + function2.apply("반값습니다."));
    }
}
