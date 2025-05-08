package java_ad3.functional;

import java.util.function.Function;

public class SideEffectMain2 {
    public static void main(String[] args) {
        Function<Integer, Integer> func = n ->{
            int result = n * 2;

            System.out.println("n = " + n + ", result = " + result);
            return result;
        };
        func.apply(10);
        func.apply(10);
        func.apply(10);
        func.apply(10);
        func.apply(10);
        func.apply(10);
    }
}
