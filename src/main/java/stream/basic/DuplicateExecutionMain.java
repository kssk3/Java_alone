package stream.basic;

import java.util.List;
import java.util.stream.Stream;

public class DuplicateExecutionMain {

    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1, 2, 3);
        stream.forEach(System.out::println); // 1. 최초 실행

        // 오류 메시지 IllegalStateException: 스트림이 이미 작동했거나 닫혔다.
        // stream.forEach(System.out::println); // 컴파일 오류 발생  stream has already been operated upon or closed

        // 대안 : 대상 리스트를 스트림으로 새로 생성해서 사용
        List<Integer> list = List.of(1, 2, 3);
        Stream.of(list).forEach(System.out::println);
        Stream.of(list).forEach(System.out::println);
    }
}
