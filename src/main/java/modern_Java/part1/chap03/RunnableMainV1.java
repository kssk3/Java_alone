package modern_Java.part1.chap03;

public class RunnableMainV1 {
    public static void main(String[] args) {

        Runnable r1 = () -> System.out.println("Hello World 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));
    }

    private static void process (Runnable r) {
        r.run();
    }
}
