package stream.basic;

import java.util.List;
import java.util.function.Predicate;
import lambda.lambda5.mystream.MyStreamV3;

public class LazyEvalMain1 {

    public static void main(String[] args) {
        List<Integer> data = List.of(1, 2, 3, 4, 5, 6);
        ex1(data);
        ex2(data);
    }

    private static void ex1(List<Integer> data) {
        System.out.println("== MySteamV3 시작 ==");
        List<Integer> result = MyStreamV3.of(data).filter(n -> {
                    boolean isEven = n % 2 == 0;
                    System.out.println("filter() 실행: " + n + " (" + isEven + ")");
                    return isEven;
                }).map(n -> {
                    int map = n * 10;
                    System.out.println("map() 실행: " + n + " (" + map + ")");
                    return map;
                })
                .toList();
        System.out.println("result = " + result);
        System.out.println("== MySteamV3 종료 ==\n");
    }

    private static void ex2(List<Integer> data) {
        System.out.println("== Stream 시작 ==");
        List<Integer> result = data.stream().filter(n -> {
            boolean isEven = n % 2 == 0;
            System.out.println("filter() 실행: " + n + " (" + isEven + ")");
            return isEven;
        }).map(n -> {
            int map = n * 10;
            System.out.println("map() 실행: " + n + " (" + map + ")");
            return map;
        }).toList();
        System.out.println("result = " + result);
        System.out.println("== Stream 종료 ==\n");
    }
}
