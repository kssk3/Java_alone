package stream.collectors;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DownStreamMain2 {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Kim", 1, 85),
                new Student("Park", 1, 70),
                new Student("Lee", 2, 70),
                new Student("Han", 2, 90),
                new Student("Hoon", 3, 90),
                new Student("Ha", 3, 89));

        // 1단계 학년별로 학생들을 그룹화
        Map<Integer, List<Student>> collect1_1 = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.toList()));
        System.out.println("collect1_1 = " + collect1_1);
        System.out.println();

        // 2단계 학년별로 성적이 높은 학생 구하기 reducing() 사용
        Map<Integer, Optional<Student>> collect2 = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,
                        Collectors.reducing((s1, s2) -> s1.getScore() > s2.getScore() ? s1 : s2)));
        System.out.println("collect2 = " + collect2);
        System.out.println();

        // 3단계 학년별로 성적이 높은 학생 구하기 maxBy() 사용
        Map<Integer, Optional<Student>> collect3 = students.stream().collect(
                Collectors.groupingBy(
                        Student::getGrade, Collectors.maxBy(Comparator.comparingInt(Student::getScore))
                        //Collectors.maxBy((s1, s2) -> s1.getScore() > s2.getScore() ? 1 : -1)
                        //Collectors.maxBy(Comparator.comparing(s -> s.getScore()))
                ));
        System.out.println("collect3 = " + collect3);
        System.out.println();

        Map<Integer, String> collect4 = students.stream().collect(Collectors.groupingBy(
                Student::getGrade,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Student::getScore)),
                        sopt -> sopt.get().getName())));
        System.out.println("collect4 = " + collect4);
        System.out.println();
    }
}
