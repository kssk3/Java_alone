package java_ad3.optional;

import java.util.Optional;

public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return getNameAsOptional(this.name).orElse("입력된 이름이 없습니다.");
    }

    public Optional<String> getNameAsOptional(String name) {
        return Optional.ofNullable(name);
    }
}
