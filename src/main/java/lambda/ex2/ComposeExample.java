package lambda.ex2;

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
