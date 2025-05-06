package java_ad3.lambda.lambda1;

import java_ad3.lambda.Procedure;

public class InstanceMain {

    public static void main(String[] args) {
        Procedure procedure1 = new Procedure() {
            @Override
            public void run() {
                System.out.println("hello lambda");
            }
        };

        System.out.println("class.class = " + procedure1.getClass());
        // class.class = class lambda.lambda1.InstanceMain$1 -> 익명 클래스는 $ 표시가 있다.
        System.out.println("class.Instance = " + procedure1);
        // class.Instance = lambda.lambda1.InstanceMain$1@452b3a41 익명 클래스의 인스턴스도 $ 표시가 포함되고 뒤에 주소값이 있다.

        System.out.println();

        Procedure procedure2 = () -> System.out.println("hello lambda");

        System.out.println("lambda.getClass() = " + procedure2.getClass());
        // lambda.getClass() = class lambda.lambda1.InstanceMain$$Lambda/0x0000007001003618 -> 람다는 $$ 표시가 2개 붙는다.
        System.out.println("lambda.instance = " + procedure2);
        // lambda.instance = lambda.lambda1.InstanceMain$$Lambda/0x0000007001003618@f6f4d33 -> 람다의 클래스 참조이다. 0x0000007001003618

    }
}
