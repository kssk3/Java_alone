package stream.collectors;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Collectors1Main {

    public static void main(String[] args) {

        System.out.println("1. 변경 가능한 toList()");
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .collect(toList());
        System.out.println("list = " + list);
        list.add(6);
        System.out.println("list = " + list);
        System.out.println();

        System.out.println("2. 변경 불가능한 unmodifiableList");
        List<Integer> unmodifiableList = Stream.of(1, 2, 3, 4, 5)
                .collect(toUnmodifiableList());
        System.out.println("unmodifiableList = " + unmodifiableList);
        // System.out.println("unmodifiableList.add(6) = " + unmodifiableList.add(6));
        // Exception in thread "main" java.lang.UnsupportedOperationException
        System.out.println();

        System.out.println("3. Set 반환 Set은 중복 값을 제거한다.");
        Set<Integer> setCollectors = Stream.of(1, 2, 3, 3, 4, 5)
                .collect(toSet());
        System.out.println("setCollectors = " + setCollectors);
        System.out.println();

        System.out.println("4. 타입 지정 (TreeSet) -> treeSet은 정렬을 유지한다.");
        TreeSet<Integer> treeSet = Stream.of(3, 1, 4, 7, 2, 3, 8, 5)
                .collect(toCollection(TreeSet::new));
        System.out.println("treeSet = " + treeSet);
    }
}
