package java_ad3.lambda.lambda1;

import java_ad3.lambda.MyFunction;

public class LambdaSimple3 {

    public static void main(String[] args) {
        // 타입 생략 전
        MyFunction myFunction1 = (int a, int b) -> a + b;
        int result1 = myFunction1.apply(1, 2);
        System.out.println("result1 = " + result1);

        // MyFunction 타입을 통해 타입 추론 가능, 람다는 타입 생략 가능
        MyFunction function2 = (a, b) -> a + b;

        int result2 = function2.apply(1, 2);
        System.out.println("result2 = " + result2);
    }
}
