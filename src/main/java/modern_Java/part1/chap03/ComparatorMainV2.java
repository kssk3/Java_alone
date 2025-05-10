package modern_Java.part1.chap03;

import java.util.Arrays;
import java.util.List;

public class ComparatorMainV2 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "A", "B");
        System.out.println("list = " + list);

        list.sort(String::compareToIgnoreCase);
        System.out.println("list = " + list);
    }
}
