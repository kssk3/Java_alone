package modern_Java.part3.chap09.Strategy;

public class ValidatorMainV2 {

    public static void main(String[] args) {
        Validator numericValidator = new Validator(s -> s.matches("//d+"));
        boolean result1 = numericValidator.validate("ddddd");
        System.out.println("result1 = " + result1); // result1 = false

        Validator lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        boolean result2 = lowerCaseValidator.validate("dddddd");
        System.out.println("result2 = " + result2); // result2 = true
    }
}



