package modern_Java.part1.chap02;


import static modern_Java.part1.chap02.Color.RED;

public class RedApplePredicate implements ApplePredicate<Apple> {
    @Override
    public boolean test(Apple t) {
        return RED.getColor().equals(t.getColor()) && t.getWeight() > 100;
    }
}
