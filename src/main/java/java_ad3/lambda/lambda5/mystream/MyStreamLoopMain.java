package java_ad3.lambda.lambda5.mystream;

import java.util.List;

public class MyStreamLoopMain {

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Apple", 100),
                new Student("Banana", 80),
                new Student("Berry", 50),
                new Student("Tomato", 40)
        );

        List<String> result1 = MyStreamV3.of(students)
                .filter(n -> n.getScore() >= 80)
                .map(n -> n.getName())
                .toList();

        // 외부 반복
        for (String s : result1) {
            System.out.println("name = " + s);
        }

        // 추가
        MyStreamV3.of(students)
                .filter(n -> n.getScore() >= 80)
                .map(n -> n.getName())
                .forEach(name -> System.out.println("name =" + name));
    }
}
