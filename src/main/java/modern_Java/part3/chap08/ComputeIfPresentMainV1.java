package modern_Java.part3.chap08;

import java.util.HashMap;
import java.util.Map;

public class ComputeIfPresentMainV1 {

    public static void main(String[] args) {

        Map<String, Integer> numbers = new HashMap<>();

        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);

        System.out.println("Before numbers = " + numbers);
        // Before numbers = {one=1, two=2, three=3}

        numbers.computeIfPresent("one", (key, value) -> value + 100);
        numbers.computeIfPresent("oone", (key, value) -> value + 100);

        System.out.println("After numbers = " + numbers);
        // After numbers = {one=101, two=2, three=3}
    }
}
