package java_ad3.lambda.lambda1;

import java_ad3.lambda.Procedure;

public class ProcedureMain2 {

    public static void main(String[] args) {
        Procedure procedure = () -> System.out.println("Hello Lambda");

        procedure.run();
    }
}
