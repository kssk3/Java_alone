package optional;

import java.util.Optional;
import java.util.Random;

public class OrElseGetMain {
    public static void main(String[] args) {
        Optional<Integer> optValue = Optional.of(100);
        Optional<Integer> optEmpty = Optional.empty();

        System.out.println("=== 단순 계산 ===");
        Integer i1 = optValue.orElse(10 + 20);
        Integer i2 = optEmpty.orElse(10 + 20);
        System.out.println("optValue.orElse(10 + 20) = " + i1);
        System.out.println("optEmpty.orElse(10 + 20) = " + i2);
        System.out.println();

        System.out.println("=== 즉시 계산 ===");
        System.out.println("값이 있는 경우 orElse");
        Integer value1 = optValue.orElse(createData());
        System.out.println("optValue.orElse(createData()) = " + value1);

        System.out.println();
        System.out.println("값이 없는 경우 orElse");
        Integer empty1 = optEmpty.orElse(createData());
        System.out.println("optEmpty.orElse(createData()) = " + empty1);
        System.out.println();

        System.out.println("=== 지연 계산 ===");
        System.out.println("값이 있는 경우 orElseGet");
        Integer value2 = optValue.orElseGet(() -> createData());
        System.out.println("optValue.orElseGet(() -> createData()) = " + value2);

        System.out.println();
        System.out.println("값이 없을 경우 orElseGet");
        Integer empty2 = optEmpty.orElseGet(() -> createData());
        System.out.println("optEmpty.orElseGet(() -> createData()) = " + empty2);
    }

    public static int createData() {
        long start = System.currentTimeMillis();
        System.out.println("데이터를 생성합니다.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int result = new Random().nextInt(100);
        long end = System.currentTimeMillis();
        System.out.println("데이터 생성이 완료되었습니다. 생성 값 = " + result + ", 데이터 생성 시간 " + (end - start) + "ms");
        return result;
    }
}
