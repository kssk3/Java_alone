package lambda.lambda3;

public class GenericMain4 {

    public static void main(String[] args) {

        GenericFunction<String, String> upperCase = s -> s.toUpperCase();
        String result1 = upperCase.apply("Hello");
        System.out.println("result1 = " + result1);

        GenericFunction<Integer, Integer> square = n -> n * n;
        Integer result2 = square.apply(3);
        System.out.println("result2 = " + result2);

        GenericFunction<String, Integer> len = s -> s.length();
        Integer result3 = len.apply("GenericTest");
        System.out.println("result3 = " + result3);

        GenericFunction2<Integer, Integer, Boolean> validate = (n1, n2) -> n1 / n2 == 1;
        Boolean result4 = validate.apply(6, 4);
        System.out.println("result4 = " + result4);
    }

    @FunctionalInterface
    interface GenericFunction<T, R> {
        R apply(T t);
    }

    @FunctionalInterface
    interface GenericFunction2<T, V, R>{
        R apply(T t, V v);
    }
}
