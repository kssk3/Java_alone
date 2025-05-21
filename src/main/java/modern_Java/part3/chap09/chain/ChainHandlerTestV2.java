package modern_Java.part3.chap09.chain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainHandlerTestV2 {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);
        String result1 = p1.handle("Aren't labdas really sexy?!!");
        System.out.println("result1 = " + result1);
        // result1 = From Raoul, Mario and Alan: Aren't lambdas really sexy?!!

        UnaryOperator<String> headerProcessing = text -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = text -> text.replace("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println("result2 = " + result2);
        // result2 = From Raoul, Mario and Alan: Aren't lambdas really sexy?!!
    }
}
