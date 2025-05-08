package modern_Java.chap02;

public class MeaningOfThis {

    public final int value = 4;

    public void doit() {
        int value = 6;

        Runnable run = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        run.run();
    }

    public static void main(String[] args) {
        MeaningOfThis meaningOfThis = new MeaningOfThis();
        meaningOfThis.doit();
    }
}
