package java_ad3.methodref;

import java.util.List;
import java_ad3.lambda.lambda5.mystream.MyStreamV3;

public class MethodRef5 {

    public static void main(String[] args) {
        List<Person> personList = List.of(new Person("Kim"),
                new Person("Park"),
                new Person("Bob"));

        List<String> result1 = MyStreamV3.of(personList)
                .map(person -> person.introduce())
                .map(s -> s.toUpperCase())
                .toList();
        System.out.println("result1 = " + result1);

        List<String> result2 = MyStreamV3.of(personList)
                .map(Person::introduce)
                .map(String::toUpperCase)
                .toList();
        System.out.println("result2 = " + result2);

        List<String> result3 = MyStreamV3.of(personList)
                .filter(s -> s.getName().length() > 3)
                .map(Person::introduce)
                .map(String::toUpperCase)
                .toList();
        System.out.println("result3 = " + result3);

    }
}
