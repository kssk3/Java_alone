package stream.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MapVsFlatMapMain {

    public static void main(String[] args) {
        List<List<Integer>> outerList = List.of(List.of(1, 2, 3),
                List.of(3, 4, 5),
                List.of(5, 6, 7));

        List<Integer> forResult = new ArrayList<>();

        for (List<Integer> list : outerList) {
            for (Integer i : list) {
                forResult.add(i);
            }
        }
        System.out.println("forResult = " + forResult);

//        List<Stream<Integer>> result1 = outerList.stream()
//                .map(list -> list.stream())
//                .toList();
//
//        System.out.println("result1 = " + result1);

        List<Integer> result2 = outerList.stream()
                .flatMap(list -> list.stream())
                .distinct().
                toList();
        System.out.println("result2 = " + result2);

//        Stream<List<Integer>> stream = outerList.stream();
//        Stream<Integer> stream2 = stream.flatMap(list -> list.stream());
//        List<Integer> list = stream2.toList();
//        System.out.println("list = " + list);

    }
}
