package java_ad3.lambda.ex1;

import java_ad3.lambda.MyFunction;

public class M5Return {
    public static void main(String[] args) {
        MyFunction add = getOperation("add");
        System.out.println("add.apply(1, 2) = " + add.apply(1, 2));

        MyFunction sub = getOperation("sub");
        System.out.println("sub.apply(1, 2) = " + sub.apply(1, 2));

        MyFunction mul = getOperation("mul");
        System.out.println("mul.apply(1, 2) = " + mul.apply(1, 2));

    }

    static MyFunction getOperation(String operator) {
        switch (operator) {
            case "add": return (a, b) -> a + b;
            case "sub": return (a, b) -> a - b;
            default: return (a, b) -> 0;
        }
    }
}
