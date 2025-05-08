package java_ad3.functional;

import java.util.function.Function;

public class SideEffectMain3 {
    public static void main(String[] args) {
        Function<Integer,Integer> func = x -> x * 2;
        int x = 10;
        Integer result = func.apply(x);
        // 부수 효과는 순수 함수와 분리해서 실행
        // 출력은 별로도 처리해 순수성을 유지
        System.out.println("x = " + x + ", result = " + result);
    }
}
