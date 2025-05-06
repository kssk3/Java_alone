package java_ad3.lambda.lambda1;

import java_ad3.lambda.Procedure;

public class ProcedureMain1 {

    public static void main(String[] args) {
        Procedure procedure = new Procedure() {
            @Override
            public void run() {
                System.out.println("hello lambda");
            }
        };

        procedure.run();
    }
}
