package optional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;

public class OptionalMain {
    public static void main(String[] args) {

//        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
//        Optional<List<Integer>> optionalNumbers = Optional.of(numbers);
//
//        List<Integer> list = optionalNumbers.stream().
//                flatMap(List::stream).
//                filter(n -> n > 2).toList();

        OptionalMain test = new OptionalMain();
        test.findUserEmailOrElse(); // 즉시 평가
        // getUserName() Called
        // result1 = Min
        System.out.println();
        test.findUserEmailOrElseGet(); // 지연 평가
        // result1 = Min

    }

    public void findUserEmailOrElse(){
        String optValue = "Min";
        String result1 = Optional.ofNullable(optValue)
                .orElse(getUserName());

        System.out.println("result1 = " + result1);
    }

    public void findUserEmailOrElseGet(){
        String optValue = "Min";
        String result1 = Optional.ofNullable(optValue)
                .orElseGet(this::getUserName);

        System.out.println("result1 = " + result1);
    }

    private String getUserName() {
        System.out.println("getUserName() Called");
        return "Unknown";
    }
}
