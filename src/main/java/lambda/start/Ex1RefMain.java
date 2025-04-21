package lambda.start;

import lambda.Procedure;
import java.util.Random;

public class Ex1RefMain {

    public static void main(String[] args) {
        Procedure dice = new Dice();
        Procedure print = new Print();

        hello(print);
        hello(dice);
    }

    public static void hello(Procedure procedure) {
        long start = System.nanoTime();

        procedure.run();

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start) + "\n");
    }

    static class Dice implements Procedure {
        @Override
        public void run() {
            int randomValue = new Random().nextInt(6) + 1;
            System.out.println("주사위 값 = " + randomValue);
        }
    }

    static class Print implements Procedure {
        @Override
        public void run() {
            for(int i = 1; i <= 3; i++){
                System.out.println("i = " + i);
            }
        }
    }
}

//class Dice implements Procedure {
//
//    @Override
//    public void run() {
//        long start = System.nanoTime();
//
//        Random random = new Random();
//        int randomValue = random.nextInt(6) + 1;
//        System.out.println("주사위 값 = " + randomValue);
//
//        long end = System.nanoTime();
//        System.out.println("실행 시간 : " + (end - start) + "\n");
//    }
//}
//
//class Print implements Procedure {
//    @Override
//    public void run() {
//        long start = System.nanoTime();
//
//        for (int i = 1; i <= 3; i++) {
//            System.out.println(i);
//        }
//
//        long end = System.nanoTime();
//        System.out.println("실행 시간 : " + (end - start) + "\n");
//    }
//}


