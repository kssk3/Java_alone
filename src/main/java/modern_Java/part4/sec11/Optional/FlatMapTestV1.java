package modern_Java.part4.sec11.Optional;

import java.util.ArrayList;
import java.util.List;

public class FlatMapTestV1 {

    public static void main(String[] args) {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company());

        // flatMap()을 올바르게 사용한 방법 1
        List<String> result1 = companies.stream()
                .flatMap(company -> company.getPeople().stream())
                .map(Person::getName)
                .toList();

        // map과 flatMap을 조합한 방법 2
        List<String> result2 = companies.stream()
                .map(Company::getPeople)
                .flatMap(List::stream)
                .map(Person::getName)
                .toList();

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);

//                .map(Company::getPeople)
//                .map(Person::getName)
//                .toArray();

//                .flatMap(Company::getPeople)
//                .flatMap(Person::getName)
//                .map()
    }


    static class Company {
        private List<Person> people = List.of(new Person("member1", 20), new Person("member2", 30));

        public List<Person> getPeople() {
            return people;
        }

        public void setPeople(List<Person> people) {
            this.people = people;
        }
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
