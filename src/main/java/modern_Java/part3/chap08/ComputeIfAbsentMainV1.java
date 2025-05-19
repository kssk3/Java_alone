package modern_Java.part3.chap08;

import java.util.HashMap;
import java.util.Map;

public class ComputeIfAbsentMainV1 {

    public static void main(String[] args) {

        Map<Integer, String> numbers = new HashMap<>();
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");

        System.out.println("Before numbers = " + numbers);
        // Before numbers = {1=one, 2=two, 3=three}

        System.out.println("numbers.computeIfAbsent(4, k -> \"four\") = " + numbers.computeIfAbsent(4, k -> "four"));
        // numbers.computeIfAbsent(4, k -> "four") = four
        System.out.println("numbers.computeIfAbsent(2, k -> \"two\") = " + numbers.computeIfAbsent(2, k -> "two"));
        // numbers.computeIfAbsent(2, k -> "two") = two

        System.out.println("After numbers = " + numbers);
        // After numbers = {1=one, 2=two, 3=three, 4=four}
    }
}
