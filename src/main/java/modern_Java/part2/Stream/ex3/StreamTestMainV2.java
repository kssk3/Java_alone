package modern_Java.part2.Stream.ex3;

import java.util.Arrays;
import java.util.List;
import modern_Java.part2.Stream.ex3.Dish.Type;

public class StreamTestMainV2 {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );

        List<Dish> list = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .toList();

        System.out.println("list = " + list);

    }
}
