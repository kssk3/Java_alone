package lambda.start;

import lambda.Procedure;
import java.util.Random;

public class Ex1myRefMain {

    public static void main(String[] args) {
        new Dice().run();
        new Print().run();
    }
}

class Dice implements Procedure {

    @Override
    public void run() {
        long start = System.nanoTime();

        Random random = new Random();
        int randomValue = random.nextInt(6) + 1;
        System.out.println("주사위 값 = " + randomValue);

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start) + "\n");
    }
}

class Print implements Procedure {
    @Override
    public void run() {
        long start = System.nanoTime();

        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
        }

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start) + "\n");
    }
}
