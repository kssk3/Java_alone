package stream.operation;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreamMain {

    public static void main(String[] args) {
        // 기본형 특화 스트림 생성 (IntStream, LongStream, DoubleStream)
        System.out.println("IntStream 생성");
        IntStream stream = IntStream.of(1, 2, 3, 4, 5);
        stream.forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 범위 생성 메서드
        System.out.println("range(1, 6) 1 ~ 6전까지 숫자 생성");
        IntStream range1 = IntStream.range(1, 6);
        range1.forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        System.out.println("rangeClosed(1, 5) 1 ~ 5까지 숫자 생성 ");
        IntStream range2 = IntStream.rangeClosed(1, 5);
        range2.forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        System.out.println("IntStream.range(1, 5).sum() = " + IntStream.range(1, 5).sum());
        System.out.println("1 ~ 4까지의 총 합");
        System.out.println();

        // 모든 통계 정보
        System.out.println("summaryStatistics() : 모든 통계 정보");
        IntSummaryStatistics state = IntStream.range(1, 5).summaryStatistics();
        System.out.println("state.getSum() = " + state.getSum());
        System.out.println("state.getAverage() = " + state.getAverage());
        System.out.println("state.getMin() = " + state.getMin());
        System.out.println("state.getMax() = " + state.getMax());
        System.out.println("state.getCount() = " + state.getCount());
        System.out.println();

        // 기본형 스트림 -> IntStream -> LongStream 매핑
        System.out.println("기본형 스트림 -> IntStream -> LongStream 매핑");
        LongStream longStream = IntStream.range(1, 5).mapToLong(n -> n * 10L);
        System.out.println("longStream.sum() = " + longStream.sum());
        System.out.println();

        // IntStream -> DoubleStream
        System.out.println("IntStream -> DoubleStream");
        double sum = IntStream.range(1, 5).mapToDouble(n -> n * 1.5).sum();
        System.out.println("sum = " + sum);
        System.out.println();

        System.out.println("기본형 박싱 -> Wrapper 객체 스트림으로 변환");
        Stream<Integer> boxed = IntStream.of(1, 2, 3, 4, 5).boxed();
        boxed.forEach(n -> System.out.print(n + " "));


    }
}
