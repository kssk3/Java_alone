package modern_Java.part2.chap06;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import modern_Java.part2.chap06.Dish.Type;

public class GropingMainV1 {

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
        // 같은 타입끼리 그룹화
        Map<Type, List<Dish>> result1 = menu.stream()
                .collect(groupingBy(Dish::getType));
        System.out.println("result1 = " + result1);

        Map<Type, Long> result2 = menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println("result2 = " + result2);

        // 조건에 맞지 않는 리스트는 반환하지 않는다.
        Map<Type, List<Dish>> result3 = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
        System.out.println("result3 = " + result3);

        // 빈 배열도 반환
        Map<Type, List<Dish>> result4 = menu.stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println("result4 = " + result4);

        Map<Type, List<String>> result5 = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println("result5 = " + result5);

        Map<Type, Map<String, List<Integer>>> result6 = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(Dish::getName, mapping(Dish::getCalories, toList()))));
        System.out.println("result6 = " + result6);

        Map<Type, Map<Integer, List<String>>> result7 = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(Dish::getCalories, mapping(Dish::getName, toList()))));
        System.out.println("result7 = " + result7);

        Map<Type, Map<Boolean, List<Dish>>> result8 = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> dish.getCalories() > 500, toList())));
        System.out.println("result8 = " + result8);

        Map<Type, Map<CaloricLevel, List<Dish>>> result9 = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {return CaloricLevel.DIET;}
                    else if (dish.getCalories() <= 700) {return CaloricLevel.NORMAL;}
                    else {return CaloricLevel.FAT;}
                })));
        System.out.println("result9 = " + result9);

        Map<Type, Optional<Dish>> result10 = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("result10 = " + result10);

        // result10 = {OTHER=Optional[pizza], MEAT=Optional[pork], FISH=Optional[salmon]}

        Map<Type, Dish> result11 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("result11 = " + result11);

        //result11 = {OTHER=pizza, MEAT=pork, FISH=salmon}
    }
}
