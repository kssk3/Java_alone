package modern_Java.chap02;

import java.util.ArrayList;
import java.util.List;

public class ApplePredicateEx02 {

    public static void main(String[] args) {
        List<Apple> inventory = List.of(new Apple("Green", 120),
                new Apple("Green", 80),
                new Apple("Red", 130),
                new Apple("Red", 100),
                new Apple("Yellow", 130));

        List<Apple> newInventory = filterApples(inventory, new RedApplePredicate());
        System.out.println("newInventory = " + newInventory);
    }

    private static <T> List<T> filterApples(List<T> inventory, ApplePredicate<T> ap) {
        List<T> newList = new ArrayList<>();
        for (T item : inventory) {
            if (ap.test(item)) {
                newList.add(item);
            }
        }
        return newList;
    }

}
