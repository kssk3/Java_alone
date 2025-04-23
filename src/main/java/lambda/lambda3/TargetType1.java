package lambda.lambda3;

public class TargetType1 {

    public static void main(String[] args) {
        FunctionA functionA = n -> "value = " + n;
        FunctionB functionB = n -> "value = " + n;

        // 이미 만들어진 functionA는 FunctionB에 대입이 불가능하다. 참조하는 부모 인터페이스가 다르기 때문 쉽게 생각하기
        // 그리고 매개변수 값도 Integer를 받고 String을 반환하는 인터페이스이기 때문에 컴파일이 정상적으로 작동하지 않는다.
        // FunctionB functionB1 = functionA
        FunctionB functionB2 = functionB;
        System.out.println("functionB.apply(15) = " + functionB.apply(15));

    }

    @FunctionalInterface
    interface FunctionA{
        String apply(Integer integer);
    }

    @FunctionalInterface
    interface FunctionB{
        String apply(Integer integer);
    }
}
