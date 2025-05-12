package modern_Java.part2.chap05;

import java.util.Arrays;
import java.util.List;
import modern_Java.part2.chap05.Dish.Type;

public class StreamMapV1 {

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

        List<String> result = menu.stream()
                .map(Dish::getName)
                .toList();

        System.out.println("result = " + result);

        List<Integer> result2 = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .toList();

        System.out.println("result2 = " + result2);
        // result2 = [4, 4, 7, 12, 4, 12, 5, 6, 6]

    }
}
