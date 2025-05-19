package modern_Java.part3.chap08;

import java.util.ArrayList;
import java.util.List;

public class RemoveIfTestV1 {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("numbers = " + numbers); //

        boolean result = numbers.removeIf(n -> n % 2 == 0);
        System.out.println("result = " + result);

        System.out.println("numbers = " + numbers);

    }
}
