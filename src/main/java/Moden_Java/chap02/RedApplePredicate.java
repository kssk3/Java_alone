package Moden_Java.chap02;


import static Moden_Java.chap02.Color.RED;

public class RedApplePredicate implements ApplePredicate<Apple> {
    @Override
    public boolean test(Apple t) {
        return RED.getColor().equals(t.getColor()) && t.getWeight() > 100;
    }
}
