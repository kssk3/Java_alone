package Optional;

import java.util.Optional;

public class OptionalCreateMain {

    public static void main(String[] args) {
        String nonNullValue = "Hello Optional";

        // 1. of() 생성자 값이 null이 확실히 아닐 때 사용. Null 값을 생성할 경우 NullPointerException 발생한다.
        Optional<String> opt1 = Optional.of(nonNullValue);
        System.out.println("opt1 = " + opt1);       // opt1 = Optional[Hello Optional]

        // 2. ofNullable 값이 있을 수 있고 없을 수 있을 때 사용한다 Null 값을 반환할 경우 Optional.empty 출력된다.
        Optional<String> opt2 = Optional.ofNullable(nonNullValue);
        Optional<String> opt3 = Optional.ofNullable(null);
        System.out.println("opt2 = " + opt2);       // opt2 = Optional[Hello Optional]
        System.out.println("opt3 = " + opt3);       // opt3 = Optional.empty

        // 3. empty() : 비어있는 Optional을 명시적으로 생성
        Optional<Object> empty = Optional.empty();
//        empty = Optional.of(nonNullValue);
        System.out.println("empty = " + empty);
    }
}
