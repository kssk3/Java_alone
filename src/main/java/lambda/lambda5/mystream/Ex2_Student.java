package lambda.lambda5.mystream;

import java.util.ArrayList;
import java.util.List;
import lambda.lambda5.filter.GenericFilter;
import lambda.lambda5.map.GenericMapper;

public class Ex2_Student {

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Apple", 100),
                new Student("Banana", 80),
                new Student("Berry", 50),
                new Student("Tomato", 40)
        );

        System.out.println("direct = " + direct(students));
        System.out.println("lambda = " + lambda(students));

    }

    private static List<String> lambda(List<Student> students) {
        List<Student> filter = GenericFilter.filter(students, n -> n.getScore() >= 80);
        List<String> mapped = GenericMapper.map(filter, s -> s.getName());
        return mapped;
    }


    private static List<String> direct(List<Student> numbers) {
        List<String> result = new ArrayList<>();
        for (Student number : numbers) {
            if (number.getScore() >= 80) {
                result.add(number.getName());
            }
        }
        return result;
    }
}
