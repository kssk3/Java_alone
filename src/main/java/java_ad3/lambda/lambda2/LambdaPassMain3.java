package java_ad3.lambda.lambda2;

import java_ad3.lambda.MyFunction;

public class LambdaPassMain3 {

    public static void main(String[] args) {
        MyFunction add = getOperation("add");
        System.out.println("add.apply(1, 2) = " + add.apply(1, 2));

        MyFunction sub = getOperation("sub");
        System.out.println("sub.apply(1, 2 = " + sub.apply(1, 2));

        MyFunction some = getOperation("xxx");
        System.out.println("some.apply(1, 2) = " + some.apply(1, 2));
    }

    // 람다를 반환하는 메서드
    static MyFunction getOperation(String operation) {
        switch (operation) {
            case "add": return (a, b) -> a + b;
            case "sub": return (a, b) -> a - b;
            default: return (a, b) -> 0;
        }
    }
}
