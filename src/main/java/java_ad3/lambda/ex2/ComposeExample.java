package java_ad3.lambda.ex2;

public class ComposeExample {
    public static void main(String[] args) {
        MyTransformer toUpper = s -> s.toUpperCase();

        MyTransformer addDeco = s -> "***" + s + "***";

        MyTransformer composeFunc = compose(toUpper, addDeco);
        System.out.println(composeFunc.transform("hello"));
    }

    public static MyTransformer compose(MyTransformer first, MyTransformer second) {
        return new MyTransformer() {
            @Override
            public String transform(String s) {
                // MyTransformer 인터페이스는 String 반환하기 때문에 가능하다.
                String intermediate = first.transform(s);
                return second.transform(intermediate);
            }
        };
    }
//        return s -> {
//            String str = first.transform(s);
//            return second.transform(str);
//        };
}
