package java_ad3.functional;

import java.util.function.Function;

public class FirstClassCitizenMain {

    public static void main(String[] args) {
//        Function<Integer, Integer> func = x -> x * x;

        Integer result1 = applyFunction(10, x -> x * x);
        System.out.println("result1 = " + result1);

        Integer result2 = getFunc().apply(10);
        System.out.println("result2 = " + result2);
    }

    // 고차 함수 : 함수를 인자로 받음
    private static Integer applyFunction(int i, Function<Integer, Integer> func) {
        return func.apply(i);
    }

    // 고차 함수 : 함수를 반환
    private static Function<Integer, Integer> getFunc() {
        return x -> x * 2;
    }
}
