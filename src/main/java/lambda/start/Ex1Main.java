package lambda.start;

import java.util.Random;

public class Ex1Main {

    public static void main(String[] args) {
        helloPrint();
        helloDice();
    }

    public static void helloDice() {
        long start = System.nanoTime();

        int randomValue = new Random().nextInt(6) + 1;
        System.out.println("주사위 값 = " + randomValue);

        long end = System.nanoTime();
        System.out.println("실행 시간: " + (end - start));
    }

    public static void helloPrint() {
        long start = System.nanoTime();

        for (int i = 0; i <= 3; i++) {
            System.out.print(i + " ");
            if(i == 3){System.out.println();}
        }

        long end = System.nanoTime();
        System.out.println("실행 시간: " + (end - start) + "\n");
    }
}
