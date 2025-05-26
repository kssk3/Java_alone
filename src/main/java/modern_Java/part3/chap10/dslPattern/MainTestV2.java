package modern_Java.part3.chap10.dslPattern;

import static modern_Java.part3.chap10.dslPattern.MethodChainingOrderBuilder.forCustomer;

public class MainTestV2 {

    public static void main(String[] args) {
        Order order = forCustomer("BigBank")
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();
    }
}
