package java_ad3.lambda.lambda1;

public class SamMain {

    public static void main(String[] args) {
        samInterface sam = ()  -> {
            System.out.println("sam.interface");
        };

        /*
        컴파일 오류 notSamInterface의 하나의 abstract void (추상 메서드)만 가질 수 있다.
        2개 이상 있을 경우 컴파일 에러 발생
        NotSamInterface notSam = () -> {
            System.out.println("notSam.interface");
        };
        */

        sam.run();
//        notSam.run();
    }
}
