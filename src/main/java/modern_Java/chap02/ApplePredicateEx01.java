package modern_Java.chap02;

import static modern_Java.chap02.Color.*;

import java.util.List;

public class ApplePredicateEx01 {

    public static void main(String[] args) {

        List<Apple> inventory = List.of(new Apple("Green", 120),
                new Apple("Green", 80),
                new Apple("Red", 130),
                new Apple("Red", 100),
                new Apple("Yellow", 130));

        List<Integer> list = inventory.stream()
                .filter(n -> n.getColor().equals(GREEN.getColor()))
                .map(Apple::getWeight)
                .toList();

        System.out.println("list = " + list);
    }
}
