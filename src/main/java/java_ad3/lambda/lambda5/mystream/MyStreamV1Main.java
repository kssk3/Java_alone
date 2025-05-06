package java_ad3.lambda.lambda5.mystream;

import java.util.List;

public class MyStreamV1Main {

    public static void main(String[] args) {
        List<Integer> number = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 짝수만 남기고, 남은 값의 2배를 반환

        returnValues(number);
        methodChain(number);
//        MyStreamV1 myStream = new MyStreamV1(number);
//        List<Integer> list = myStream.filter(n -> n % 2 == 0).map(n -> n * 2).toList();
//        System.out.println("list = " + list);
    }

    private static void returnValues(List<Integer> numbers) {
        MyStreamV1 stream = new MyStreamV1(numbers);
        MyStreamV1 filteredStream = stream.filter(n -> n % 2 == 0);
        System.out.println("filteredStream = " + filteredStream.toList());

        MyStreamV1 mappedStream = filteredStream.map(n -> n * 2);
        System.out.println("mappedStream = " + mappedStream.toList());
    }

    // filter(), map() 메서드의 경우 자신을 반환하기 때문에 메서드 체이닝이 가능하다.
    private static void methodChain(List<Integer> numbers) {
        MyStreamV1 stream = new MyStreamV1(numbers);
        List<Integer> list = stream.filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .toList();
        System.out.println("list = " + list);
    }
}
