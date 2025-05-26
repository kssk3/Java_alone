package modern_Java.part3.chap10.dsl2;

import modern_Java.part3.chap10.dslPattern.Order;

public class MainTestV1 {

    public static void main(String[] args) {

        Order order = new Order();

        double value = new TaxCalculator1().withTaxRegional()
                .withTaxSurcharge()
                .calculate(order);


    }
}
