package java_ad3.lambda.start;

public class Ex0Main {
    public static void main(String[] args) {
//        helloJava();
//        helloSpring();
        helloSomething("Hello Java");
        helloSomething("Hello Spring");
    }

    public static void helloSomething(String text) {
        System.out.println("프로그램 시작");
        System.out.println(text);
        System.out.println("프로그램 종료");
    }

    public static void helloJava() {
        System.out.println("프로그램 시작");
        System.out.println("Hello Java");
        System.out.println("프로그램 종료");
    }

    public static void helloSpring() {
        System.out.println("프로그램 시작");
        System.out.println("Hello Spring");
        System.out.println("프로그램 종료");
    }
}
