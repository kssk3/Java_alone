package Moden_Java.chap03;

import Moden_Java.chap02.Apple;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorMainV1 {

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple("Green", 120),
                new Apple("Green", 80),
                new Apple("Red", 130),
                new Apple("Red", 100),
                new Apple("Yellow", 130));

        System.out.println("inventory = " + inventory);

//        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
        inventory.sort(Comparator.comparingInt(Apple::getWeight));

        System.out.println("inventory = " + inventory);
    }
}
