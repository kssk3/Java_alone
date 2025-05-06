package java_ad3.lambda.ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapExampleEx2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "java", "java_ad3/lambda");
        System.out.println("원본 리스트 : " + list);

        StringFunction sf1 = text -> text.toUpperCase();
        System.out.println("대문자 변환 결과: " + map(list, sf1));

        StringFunction sf2 = text -> "***" + text + "***";
        System.out.println("특수문자 데코 결과: " + map(list, sf2));
    }

    static List<String>map(List<String> list, StringFunction function) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(function.apply(s));
        }
        return result;
    }
}

