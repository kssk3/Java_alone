package lambda.start;

import lambda.Procedure;
import java.util.Random;

// 익명 클래스 사용
public class Ex1RefMainV2 {

    public static void main(String[] args) {
        Procedure dice = new Procedure() {
            @Override
            public void run() {
                int randomValue = new Random().nextInt(6) + 1;
                System.out.println("주사위 값 = " + randomValue);
            }
        };
        Procedure print = new Procedure() {
            @Override
            public void run() {
                for(int i = 1; i <= 3; i++){
                    System.out.println("i = " + i);
                }
            }
        };


        hello(print);
        hello(dice);
    }

    public static void hello(Procedure procedure) {
        long start = System.nanoTime();

        procedure.run();

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start));
    }

}
