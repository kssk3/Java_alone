package modern_Java.chap02.Behavior_Parameterization;

import modern_Java.chap02.Apple;
import java.util.List;

public class ComparatorMainV1 {

    public static final String RED = "Red";
    public static void main(String[] args) {
        List<Apple> inventory = List.of(new Apple("Green", 120),
                new Apple("Green", 80),
                new Apple("Red", 130),
                new Apple("Red", 100),
                new Apple("Yellow", 130));

        System.out.println("inventory = " + inventory);

//        inventory.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple a1, Apple a2) {
//                return a2.getWeight() - a1.getWeight();
//            }
//        });
//
//        System.out.println("inventory = " + inventory);

        List<Apple> newInventory = inventory.stream()
                .filter(a -> a.getColor().equals(RED))
                .sorted((a1, a2) -> a2.getWeight() - a1.getWeight())
                .toList();

        System.out.println("newInventory = " + newInventory);
    }
}
