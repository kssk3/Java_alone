package lambda.ex3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class MapExampleEx2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "java", "lambda");
        System.out.println("원본 리스트 : " + list);

        UnaryOperator<String> sf1 = text -> text.toUpperCase();
        System.out.println("대문자 변환 결과: " + map(list, sf1));

        UnaryOperator<String> sf2 = text -> "***" + text + "***";
        System.out.println("특수문자 데코 결과: " + map(list, sf2));
    }

    static List<String>map(List<String> list, UnaryOperator<String> function) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(function.apply(s));
        }
        return result;
    }
}

