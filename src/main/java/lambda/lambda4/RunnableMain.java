package lambda.lambda4;

import java.util.Random;

public class RunnableMain {

    public static void main(String[] args) {

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                int random = new Random().nextInt(6) + 1;
                System.out.println("random = " + random);
            }
        };
        runnable1.run();

        Runnable runnable2 = () -> {
            int random = new Random().nextInt(10) + 1;
            System.out.println("random = " + random);
        };
        runnable2.run();
    }
}
