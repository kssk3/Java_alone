package lambda.ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapExampleEx1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "java", "lambda");
        System.out.println("원본 리스트 : " + list);

        StringFunction sf1 = new StringFunction() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        List<String> upperString = map(list, sf1);
        System.out.println("대문자 변환 결과 : " + upperString);

        StringFunction sf2 = new StringFunction() {
            @Override
            public String apply(String s) {
                return "***" + s + "***";
            }
        };

        List<String> star = map(list, sf2);
        System.out.println("특수문자 데코 결과 : " + star);
    }

    static List<String>map(List<String> list, StringFunction function) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(function.apply(s));
        }
        return result;
    }
}

