package modern_Java.part3.chap09.strategy;

public class ValidatorMainV1 {

    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean result1 = numericValidator.validate("dddddddd");
        System.out.println("result1 = " + result1);
        // result1 = false

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean result2 = lowerCaseValidator.validate("dddddddd");
        System.out.println("result2 = " + result2);
        // result2 = true
    }
}



