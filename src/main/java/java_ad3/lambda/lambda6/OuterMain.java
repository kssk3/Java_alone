package java_ad3.lambda.lambda6;

public class OuterMain {

    private String name = "외부클래스";

    public static void main(String[] args) {
        OuterMain outerMain = new OuterMain();
        System.out.println("[외부 클래스]" + outerMain);
        System.out.println("======================================");
        outerMain.execute();
    }

    private void execute() {
        Runnable anonymous = new Runnable() {
            String name = "익명클래스";
            @Override
            public void run() {
                System.out.println("[익명 클래스] this = " + this);
                System.out.println("[익명 클래스] this.getClass = " + this.getClass());
                System.out.println("[익명 클래스] this.name = " + this.name);
            }
        };

        Runnable lambda = () -> {
            String name = "람다 클래스";
            System.out.println("[람다] this = " + this);
            System.out.println("[람다] this.getClass = " + this.getClass());
            System.out.println("[람다] this.name = " + this.name);
        };
        anonymous.run();
        System.out.println("======================================");
        lambda.run();
    }
}
