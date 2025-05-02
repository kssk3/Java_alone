package optional;

import java.util.Optional;

public class OptionalProcessingMain {
    public static void main(String[] args) {

        Optional<String> optValue = Optional.of("Hello");
        Optional<String> optEmpty = Optional.empty();

        System.out.println("=== ifPresent() ===");
        System.out.println("매개 변수로 Consumer()를 받는다.");
        optValue.ifPresent(n -> System.out.println("optValue 값 = " + n));
        optEmpty.ifPresent(n -> System.out.println("optEmpty 값 = " + n));
        System.out.println();

        System.out.println("=== ifPresentOrElse() ===");
        System.out.println("매개 변수로 Consumer(), Runnable()를 받는다. ");
        optValue.ifPresentOrElse(n -> System.out.println("optValue 값 = " + n), // optValue 값 = Hello
                () -> System.out.println("optValue 값이 비어있습니다."));
        optEmpty.ifPresentOrElse(n -> System.out.println("optEmpty 값 = " + n),
                () -> System.out.println("optEmpty 값이 비어있습니다."));// optEmpty 값이 비어있습니다.
        System.out.println();

        System.out.println("=== map() ===");
        System.out.println("매개 변수로 Function()를 받는다.");
        Optional<Integer> lengthOpt1 = optValue.map(String::length);
        Optional<Integer> lengthOpt2 = optEmpty.map(String::length);
        System.out.println("lengthOpt1 = " + lengthOpt1);// lengthOpt1 = Optional[5]
        System.out.println("lengthOpt2 = " + lengthOpt2);// lengthOpt2 = Optional.empty
        System.out.println();

        System.out.println("=== flatMap() ===");
        System.out.println("매개 변수로 Function()과 Optional()을(를) 받는다.");
        // map()과 유사하나 flatMap()의 경우 평탄화 작업한다. 아래 코드를 보며 차이를 비교해보자.
        Optional<Optional<String>> optMap1 = optValue.map(n -> Optional.of(n));
        Optional<Optional<String>> optMap2 = optEmpty.map(n -> Optional.of(n));
        System.out.println("optValue.map() = " + optMap1);// optValue.map() = Optional[Optional[Hello]]
        System.out.println("optEmpty.map() = " + optMap2);// optEmpty.map() = Optional.empty

        Optional<String> optFlatted1 = optValue.flatMap(n -> Optional.of(n));
        Optional<String> optFlatted2 = optEmpty.flatMap(n -> Optional.of(n));
        System.out.println("optValue.flatMap() = " + optFlatted1);// optValue.flatMap() = Optional[Hello]
        System.out.println("optEmpty.flatMap() = " + optFlatted2);// optEmpty.flatMap() = Optional.empty

        System.out.println("=== stream() ===");
        optValue.stream().forEach(System.out::println); // Hello
        optEmpty.stream().forEach(System.out::println); // 값이 없으면 동작하지 않는다.
    }
}
