package modern_Java.part3.chap10.dsl2;

import modern_Java.part3.chap10.dslPattern.Order;

public class MainTestV2 {

    public static void main(String[] args) {

        Order order = new Order();
        double value = new TaxCalculator2().with(Tax::regional)
                .with(Tax::surcharge)
                .calculate(order);
    }
}
