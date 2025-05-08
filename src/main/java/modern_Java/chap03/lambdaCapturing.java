package modern_Java.chap03;

public class lambdaCapturing {

    public static void main(String[] args) {

        int portNumber = 8080;
        Runnable r = () -> System.out.println(portNumber);
        portNumber++;
        process(r);
    }

    private static void process(Runnable r) {r.run();}
}
