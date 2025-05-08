package Moden_Java.chap02;

public class ApplePrintHeavyWeight implements AppleFormater<Apple> {

    @Override
    public String format(Apple apple) {
        String isHeavyWeightApple = apple.getWeight() > 110 ? "heavy" : "light";

        return "A " + isHeavyWeightApple + " " + apple.getColor() + " apple";
    }
}
