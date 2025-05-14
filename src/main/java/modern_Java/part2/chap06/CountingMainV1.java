package modern_Java.part2.chap06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import modern_Java.part2.chap06.Dish.Type;

public class CountingMainV1 {

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

        Long count1 = menu.stream().
                collect(Collectors.counting());
        System.out.println("count1 = " + count1);

        long count2 = menu.stream().count();
        System.out.println("count2 = " + count2);

        Comparator<Dish> dishComparator =
                Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> result1 = menu.stream()
                .collect(Collectors.maxBy(dishComparator));
        System.out.println("result1 = " + result1);

        Optional<Dish> result2 = menu.stream()
                .max(dishComparator);
        System.out.println("result2 = " + result2);

        Integer result3 = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("result3 = " + result3);

        int result4 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("result4 = " + result4);

//        count1 = 9
//        count2 = 9
//        result1 = Optional[pork]
//        result2 = Optional[pork]
//        result3 = 4200
//        result4 = 4200

        String result5 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println("result5 = " + result5);

        String result6 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.reducing((a, b) -> a + b)).orElse(null);

        System.out.println("result6 = " + result6);
    }
}
