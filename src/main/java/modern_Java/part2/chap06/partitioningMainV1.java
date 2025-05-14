package modern_Java.part2.chap06;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import modern_Java.part2.chap06.Dish.Type;

public class partitioningMainV1 {
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

        Map<Boolean, List<Dish>> result1 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println("result1 = " + result1);

        Map<Boolean, List<Dish>> result2 = menu.stream()
                .collect(partitioningBy(dish -> dish.getCalories() > 500));
        System.out.println("result2 = " + result2);

        // result1 = {false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, season fruit, pizza]}
        // result2 = {false=[chicken, rice, season fruit, prawns, salmon], true=[pork, beef, french fries, pizza]}

        Map<Boolean, Map<Type, List<Dish>>> result3 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println("result3 = " + result3);
        // result3 = {false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]}, true={OTHER=[french fries, rice, season fruit, pizza]}}

        Map<Boolean, Map<String, List<Integer>>> result4 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getName, mapping(Dish::getCalories, toList()))));
        System.out.println("result4 = " + result4);
        // result4 = {false={chicken=[400], salmon=[450], beef=[700], pork=[800], prawns=[300]}, true={season fruit=[120], pizza=[550], rice=[350], french fries=[530]}}

        Map<Boolean, Dish> result5 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("result5 = " + result5);
        // result5 = {false=pork, true=pizza}

    }
}
