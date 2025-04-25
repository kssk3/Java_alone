package lambda.lambda5.filter;

import java.util.List;

public class FilterMainV5 {

    public static void main(String[] args) {
        // 숫자 사용 필터
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numberResult = GenericFilter.filter(numbers, x -> x % 2 == 0);
        System.out.println("numberResult = " + numberResult);

        List<String> Strings = List.of("A", "BBB", "CC", "DD", "EEEE", "FFF", "GG");
        List<String> StringResult = GenericFilter.filter(Strings, x -> x.length() >= 2);
        System.out.println("StringResult = " + StringResult);
    }

}



