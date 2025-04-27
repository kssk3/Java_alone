package lambda.lambda5.mystream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lambda.lambda5.filter.GenericFilter;
import lambda.lambda5.map.GenericMapper;

public class Ex1_Number {
    public static void main(String[] args) {

        // 짝수만 남기고, 남은 값의 2배를 반환
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("direct = " + direct(numbers));

        System.out.println("lambda = " + lambda(numbers));

    }

    public static List<Integer> lambda(List<Integer> list) {
        List<Integer> filteredList = GenericFilter.filter(list, n -> n % 2 == 0); // 짝수 필터
        List<Integer> mappedList = GenericMapper.map(filteredList, n -> n * 2);
        return mappedList;
    }

    public static List<Integer> direct(List<Integer> list) {
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer i : list) {
            if (i % 2 == 0) {
                i *= 2;
                evenNumbers.add(i);
            }
        }
        return evenNumbers;
    }
}
