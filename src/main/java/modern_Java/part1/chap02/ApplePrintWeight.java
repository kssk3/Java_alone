package modern_Java.part1.chap02;

public class ApplePrintWeight implements AppleFormater<Apple> {
    @Override
    public String format(Apple apple) {
        return apple.getColor() + "색 사과 " + apple.getWeight() + "kg 입니다.";
    }
}
