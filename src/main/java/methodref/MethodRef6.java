package methodref;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MethodRef6 {

    public static void main(String[] args) {
        // 4. 임의 객체의 인스턴스 메서드 참조(특정 타입의)
        Person person = new Person("Kim");

        // 람다
        BiFunction<Person, Integer, String> fun1 =
                (Person p, Integer number) -> p.introduceWithNumber(number);

        System.out.println("person.introduceWithNumber = "  + fun1.apply(person, 1));

        BiFunction<Person, Integer, String> fun2 = Person::introduceWithNumber; // 타입::메서드명
        System.out.println("person.introduceWithNumber = "  + fun2.apply(person, 1));

        Function<String, Integer> func = String::length;
        System.out.println("person.introduceWithNumber = "  + func.apply("Kim"));
    }
}
