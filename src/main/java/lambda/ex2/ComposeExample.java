package lambda.ex2;

public class ComposeExample {
    public static void main(String[] args) {
        // f1 : 대문자로 변환
        MyTransformer toUpper = s -> s.toUpperCase();

        // f2 : 앞 뒤에 "**" 붙이기
        MyTransformer addDeco = s -> "**" + s + "**";

        MyTransformer composeFunc = compose(toUpper, addDeco);
        System.out.println(composeFunc.transform("hello"));

    }

    public static MyTransformer compose(MyTransformer f1, MyTransformer f2) {
//        return new MyTransformer() {
//            @Override
//            public String transform(String s) {
//                String intermediate = f1.transform(s);
//                return f2.transform(intermediate);
//            }
//        };
        return s -> {
            String intermediate = f1.transform(s);
            return f2.transform(intermediate);
        };
    }
}
