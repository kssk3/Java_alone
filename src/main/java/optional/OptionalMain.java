package optional;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalMain {

    static String email = "16516541-151654195-152121";
    public static void main(String[] args) {

        Optional<String> emailA = Optional.of(email);
        Optional<String> emailB = Optional.of(email);

        System.out.println("=== 즉시 연산 ===");
        String result1 = emailA.orElse(findOrElseEmail());
        System.out.println("result1 = " + result1);
        System.out.println();

        System.out.println("=== 지연 연산 ===");
        String result2 = emailB.orElseGet(() -> findOrElseGetEmail());
        //String result2 = emailB.orElseGet(OptionalMain::findOrElseGetEmail);
        System.out.println("result2 = " + result2);

    }
    static String findOrElseEmail() {
        System.out.println("이메일 데이터를 찾고 있습니다.");
        String email = "0000000_000000_00000";
        return email;
    }

    static String findOrElseGetEmail() {
        System.out.println("이메일 데이터를 찾고 있습니다.");
        String email = "0000000_000000_00000";
        return email;
    }
}
