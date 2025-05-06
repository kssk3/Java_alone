package java_ad3.optional;

import java.util.Optional;
import org.w3c.dom.ls.LSOutput;

public class OptionalMain {
    public static void main(String[] args) {

        Optional<Integer> optValue = Optional.ofNullable(100);
        Optional<Object> optEmpty = Optional.ofNullable(null);

        System.out.println("optValue = " + optValue);
        System.out.println("optEmpty = " + optEmpty);
        System.out.println();

        optValue.ifPresentOrElse(
                n -> System.out.println("optValue.ifPresentOrElse = " + optValue),
                () -> System.out.println(Optional.empty()));

        optEmpty.ifPresentOrElse(
                n -> System.out.println(optEmpty),
                () -> System.out.println("optEmpty.ifPresentOrElse " + Optional.empty())
        );
        System.out.println();

        System.out.println("optValue.or(() -> Optional.of(500)) = "+optValue.or(()->Optional.of(500)));
        System.out.println("optEmpty.or(() -> Optional.of(500)) = "+optEmpty.or(()->Optional.of(500)));



        }
}
