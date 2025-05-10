package modern_Java.part2.Stream.ex1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StreamTestMainV1 {

    public static void main(String[] args) {

        List<Dish> menu = List.of(
                new Dish("말랑카우", 360),
                new Dish("스파게티", 410),
                new Dish("스테이크", 560),
                new Dish("차돌박이 솥밥", 370));

        System.out.println("menu = " + menu);

        List<Dish> lowCalorieDish = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalorieDish.add(dish);
            }
        }

        Collections.sort(lowCalorieDish, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getCalories() - o2.getCalories();
            }
        });

        System.out.println("lowCalorieDish = " + lowCalorieDish);

        List<Dish> lowCaloriesNames = new ArrayList<>();
        for (Dish dish : lowCalorieDish) {
            lowCaloriesNames.add(dish.getName());
        }

        System.out.println("lowCaloriesNames = " + lowCaloriesNames);

    }
}
