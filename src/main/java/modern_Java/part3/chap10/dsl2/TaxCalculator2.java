package modern_Java.part3.chap10.dsl2;

import java.util.function.DoubleUnaryOperator;
import modern_Java.part3.chap10.dslPattern.Order;

public class TaxCalculator2 {

    public DoubleUnaryOperator taxFunction = d -> d;

    public TaxCalculator2 with(DoubleUnaryOperator f){
        taxFunction = taxFunction.andThen(f);
        return this;
    }

    public double calculate(Order order) {
        return taxFunction.applyAsDouble(order.getValue());
    }
}
