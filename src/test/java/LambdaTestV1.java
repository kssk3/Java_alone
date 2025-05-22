import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LambdaTestV1 {


    @Test
    void filterTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = numbers.stream().filter(number -> number % 2 == 0).toList();
        List<Integer> smallerThanThree = numbers.stream().filter(number -> number < 3).toList();

        Assertions.assertEquals(Arrays.asList(2,4), even);
        Assertions.assertEquals(Arrays.asList(1,2), smallerThanThree);
    }
}
