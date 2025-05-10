package modern_Java.chap03;

public class LambdaThis {

    public static void main(String[] args) {
        new LambdaThis().test();
    }

    public void test() {
        Runnable anon = new Runnable() {
            @Override
            public void run() {
                check(this);
            }
        };
        anon.run();

        Runnable lambda = () -> check(LambdaThis.this);
        lambda.run();
    }

    private void check(Object object) {
        System.out.println(object instanceof Runnable);
    }
}
