package modern_Java.chap03.ex;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FruitMainTest {

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
    }
    public static void main(String[] args) {
        Fruit apple = getFruit("apple", 90);

        System.out.println("apple = " + apple);
    }

    private static Fruit getFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }

}
