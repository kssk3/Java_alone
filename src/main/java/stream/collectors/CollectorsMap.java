package stream.collectors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsMap {
    public static void main(String[] args) {


        Map<String, Integer> map1 = Stream.of("Apple", "Banana", "Orange")
                .collect(Collectors.toMap(
                        name -> name,               // KeyMapper
                        name -> name.length()));    // ValueMapper
        System.out.println("map1 = " + map1);

        // 키 중복 예외 발생
//        Map<String, Integer> map2 = Stream.of("Apple", "Apple", "Orange")
//                .collect(Collectors.toMap(
//                        name -> name,
//                        name -> name.length()));
//        System.out.println("map2 = " + map2);
        // Exception in thread "main" java.lang.IllegalStateException: Duplicate key Apple (attempted merging values 5 and 5)

        // 키 중복 병합
        Map<String, Integer> map3 = Stream.of("Apple", "Apple", "Orange")
                .collect(Collectors.toMap(
                        name -> name,                       // KeyMapper
                        name -> name.length(),              // ValueMapper
                        // 기본 값과 새로운 값이 같은 경우 새로운 값으로 반환
                        // 중복될 경우 기존 값과 새로운 값 더하기
                        (originalValue, newValue) -> originalValue + newValue)); // BinaryOperator
        System.out.println("map3 = " + map3);

        // 타입 지정
        LinkedHashMap<String, Integer> linkedHashMap = Stream.of("Apple", "Apple", "Orange")
                .collect(Collectors.toMap(
                        name -> name,
                        name -> name.length(),
                        (oldValue, newValue) -> oldValue + newValue, LinkedHashMap::new));
        System.out.println("linkedHashMap = " + linkedHashMap.getClass());
    }
}
