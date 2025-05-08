package modern_Java.chap02;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ApplePredicateEx04 {

    public static void main(String[] args) {
        List<Apple> inventory = List.of(new Apple("Green", 120),
                new Apple("Green", 80),
                new Apple("Red", 130),
                new Apple("Red", 100),
                new Apple("Yellow", 130));

        System.out.println("inventory = " + inventory);



//        List<Apple> result1 = filter(inventory, n -> n.getWeight() > 110 && n.getColor().equals(RED.getColor()));
//        System.out.println("result1 = " + result1);
    }

    private static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
        List<T> newList = new ArrayList<>();
        for (T item : inventory) {
            if (p.test(item)) {
                newList.add(item);
            }
        }
        return newList;
    }


}
