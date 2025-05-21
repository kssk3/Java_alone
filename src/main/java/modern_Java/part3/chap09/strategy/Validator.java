package modern_Java.part3.chap09.strategy;

public class Validator {
    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String value) {
        return validationStrategy.execute(value);
    }
}
