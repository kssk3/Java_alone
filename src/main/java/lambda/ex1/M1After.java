package lambda.ex1;


public class M1After {
    public static void main(String[] args) {
        hello("Good Morning");
        hello("Good Afternoon");
        hello("Good Evening");
    }

    static void hello(String message) {
        System.out.println("===시작===");
        System.out.println(message);
        System.out.println("===끝===");
    }
}

