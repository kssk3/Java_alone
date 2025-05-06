package java_ad3.lambda.ex2;

public class BuildGreeterExample {

    public static void main(String[] args) {

        StringFunction hello = buildGreeter("Hello");
        System.out.println(hello.apply("Java"));

        StringFunction lambda = buildGreeter("Hi");
        System.out.println(lambda.apply("Lambda"));
    }

    public static StringFunction buildGreeter(String greeting) {
        return name -> greeting + ", " + name;

//        return new StringFunction() {
//            @Override
//            public String apply(String s) {
//                return greeting + ", " + s;
//            }
//        };
    }
}
