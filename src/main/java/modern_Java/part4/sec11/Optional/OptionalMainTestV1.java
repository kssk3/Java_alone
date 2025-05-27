package modern_Java.part4.sec11.Optional;

import java.util.Optional;

public class OptionalMainTestV1 {

    public static void main(String[] args) {

        Person person = new Person();

//        Optional<Person> optValue = Optional.of(person);
//        String result1 = optValue.map(Person::getCar)
//                .map(Car::getInsurance)   -- > Car 객체가 없을 경우 Optional<Car>를 반환하므로 컴파일 오류
//                .map(Insurance::getName)  -- > Insuracne 객체가 없을 경우 Optional<Insurance>를 반환하므로 컴파일 오류
//                .toString();

        Optional<Person> optValue = Optional.of(person);
        String result2 = optValue.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");

        System.out.println("result2 = " + result2);
    }
}
