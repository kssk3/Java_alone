package modern_Java.part3.chap08;

import java.util.ArrayList;
import java.util.List;

public class ReplaceAllTestV1 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");
        list.add("kiwi");

        System.out.println("Before replaceAll() = " + list);
        // Before replaceAll() = [apple, banana, orange, kiwi]

        list.replaceAll(x -> Character.toUpperCase(x.charAt(0)) + x.substring(1));

        System.out.println("After replaceAll() = " + list);
        // After replaceAll() = [Apple, Banana, Orange, Kiwi]
    }
}
