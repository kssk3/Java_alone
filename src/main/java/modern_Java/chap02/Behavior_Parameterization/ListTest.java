package modern_Java.chap02.Behavior_Parameterization;

import java.util.Arrays;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.add(6);
        list.add(7);
        list.add(8);

        System.out.println("list = " + list);
    }
}
