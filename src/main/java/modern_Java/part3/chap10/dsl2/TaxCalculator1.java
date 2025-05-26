package modern_Java.part3.chap10.dsl2;

import modern_Java.part3.chap10.dslPattern.Order;

public class TaxCalculator1 {

    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public TaxCalculator1 withTaxRegional() {
        this.useRegional = true;
        return this;
    }

    public TaxCalculator1 withTaxGeneral() {
        this.useGeneral = true;
        return this;
    }

    public TaxCalculator1 withTaxSurcharge() {
        this.useSurcharge = true;
        return this;
    }

    public static double calculate(Order order, boolean useRegional, boolean useGeneral, boolean useSurcharge) {
        double value = order.getValue();
        if (useRegional) {value = Tax.regional(value);}
        if (useGeneral) {value = Tax.general(value);}
        if (useSurcharge) {value = Tax.surcharge(value);}
        return value;
    }

    public double calculate(Order order) {
        return calculate(order, useRegional, useGeneral, useSurcharge);
    }

}
