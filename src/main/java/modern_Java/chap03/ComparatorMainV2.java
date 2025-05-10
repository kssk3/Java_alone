package modern_Java.chap03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import modern_Java.chap02.Apple;

public class ComparatorMainV2 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "A", "B");
        System.out.println("list = " + list);

        list.sort(String::compareToIgnoreCase);
        System.out.println("list = " + list);
    }
}
