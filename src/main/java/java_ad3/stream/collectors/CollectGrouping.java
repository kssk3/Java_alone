package java_ad3.stream.collectors;

import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectGrouping {

    public static void main(String[] args) {
        List<Book> books = List.of(new Book("하퍼 리", "앵무새 죽이기", 14_000),
                new Book("김훈", "칼의 노래", 9_000),
                new Book("한강", "소년이 온다", 11_000));

        Map<String, List<String>> collect1 = books.stream().collect(Collectors.groupingBy(Book::getAuthor,
                Collectors.collectingAndThen(Collectors.mapping(Book::getName, Collectors.toList()),Collections::unmodifiableList)));
        System.out.println("collect1 = " + collect1);
        System.out.println();


        Map<String, List<Book>> collect3 = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        System.out.println("collect3 = " + collect3);
        System.out.println();

        Map<Boolean, List<Book>> collect4 = books.stream().
                collect(Collectors.partitioningBy(n -> n.getPrice() > 10000));
        System.out.println("collect4 = " + collect4);

        IntSummaryStatistics collect2 = books.stream().collect(Collectors.summarizingInt(Book::getPrice));
        System.out.println("collect2 = " + collect2);
        System.out.println("collect2.getMax() = " + collect2.getMax());
        System.out.println("collect2.getMin() = " + collect2.getMin());
        System.out.println("collect2.getSum() = " + collect2.getSum());
        System.out.println("collect2.getAverage() = " + collect2.getAverage());
        System.out.println("collect2.getCount() = " + collect2.getCount());


    }
}
