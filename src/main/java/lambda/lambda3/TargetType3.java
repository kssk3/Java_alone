package lambda.lambda3;

import java.util.function.Function;

// 자바가 기본으로 제공하는 Function 대입
public class TargetType3 {

    public static void main(String[] args) {
        Function<Integer, String> functionA = n -> "value = " + n;
        System.out.println(functionA.apply(10));

        Function<Integer, String> functionC = functionA;
        System.out.println(functionC.apply(45));
    }
}
