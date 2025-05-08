package Moden_Java.chap01;

import Moden_Java.chap02.Apple;
import java.util.List;

public class FilterMain1 {

    private static final String GREEN = "Green";

    public static void main(String[] args) {
        List<Apple> inventory = List.of(
                new Apple("Green", 80),
                new Apple("Red", 80),
                new Apple("Yellow", 70));

        List<String> list = inventory.stream()
                .filter(n -> n.getColor().equals(GREEN))
                .map(Apple::getColor)
                .toList();

        System.out.println("list = " + list);
    }
}
