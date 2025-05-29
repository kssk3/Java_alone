package modern_Java.part4.sec11.Optional;

import java.util.Optional;

public class OptionalMainTestV2 {

    public Optional<Insurance> nullSafeInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(x -> findCheapestInsurance(p, x)));
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance();
    }
}
