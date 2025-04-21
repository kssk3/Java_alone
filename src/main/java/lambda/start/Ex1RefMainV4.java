package lambda.start;

import lambda.Procedure;
import java.util.Random;

// 람다 사용
public class Ex1RefMainV4 {


    public static void main(String[] args) {

        final String text = "\n안녕하세요\n";

        hello(() ->  {
                final int randomValue = new Random().nextInt(6) + 1;
                System.out.println("주사위 값 = " + randomValue);
        });

        hello(() -> {
            for(int i = 1; i <= 3; i++){
                System.out.println("i = " + i);
            }
        });

        hello(() -> System.out.println(text));
    }

    public static void hello(Procedure procedure) {
        long start = System.nanoTime();

        procedure.run();

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start));
    }

}
