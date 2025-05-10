package modern_Java.part2.Stream.ex1;

import java.util.Comparator;
import java.util.List;

public class StreamTestMainV2 {

    public static void main(String[] args) {

        List<Dish> menu = List.of(
                new Dish("말랑카우", 360),
                new Dish("스파게티", 410),
                new Dish("스테이크", 560),
                new Dish("차돌박이 솥밥", 370));

        System.out.println("menu = " + menu);
        System.out.println();

        List<Dish> lowCaloriesMenus = menu.stream()
                .filter(n -> n.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .toList();

        System.out.println("lowCaloriesMenus = " + lowCaloriesMenus);
    }
}
