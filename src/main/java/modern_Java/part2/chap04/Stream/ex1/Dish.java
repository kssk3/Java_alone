package modern_Java.part2.chap04.Stream.ex1;

public class Dish {
    private String name;
    private Integer calories;

    public Dish(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public Dish getName() {
        return this;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
