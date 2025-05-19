package modern_Java.part3.chap08;

import java.util.Map;
import java.util.Map.Entry;

public class GetOrDefaultMainV1 {

    public static void main(String[] args) {

        Map<Integer, String> numbers = Map.ofEntries(
                Map.entry(1, "One"),
                Map.entry(2, "Two"),
                Map.entry(3, "Three"));
        System.out.println("numbers = " + numbers);

        System.out.println("numbers.getOrDefault(3, \"Three\") = " + numbers.getOrDefault(3, "값이 없습니다."));
        System.out.println("numbers.getOrDefault(4, \"Four\") = " + numbers.getOrDefault(4, "값이 없습니다."));
    }
}
