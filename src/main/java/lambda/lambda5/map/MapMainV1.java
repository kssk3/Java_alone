package lambda.lambda5.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapMainV1 {

    public static void main(String[] args) {
        List<String> list = List.of("1", "12", "123", "1234");
        List<Integer> numbers = map(list, n -> Integer.parseInt(n));
        System.out.println("numbers = " + numbers);

        List<Integer> lengths = map(list, n -> n.length());
        System.out.println("lengths = " + lengths);

    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function){
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
