package modern_Java.part3.chap08;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapEntryMainV1 {

    public static void main(String[] args) {

        Map<Integer, String> numbers1 = Map.ofEntries(Map.entry(1, "one"), Map.entry(2, "two"),
                Map.entry(3, "three"));

        System.out.println("numbers1 = " + numbers1);
        //numbers1 = {3=three, 2=two, 1=one}

        List<Entry<Integer, String>> result1 = numbers1.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .toList();
        System.out.println("result1 = " + result1);
        // result1 = [1=one, 2=two, 3=three]

        Map<Integer, String> numbers2 = Map.of(2, "two", 1, "one", 3, "three");

        System.out.println("numbers2 = " + numbers2);
        // numbers2 = {3=three, 2=two, 1=one} // 맵의 값의 순서는 매번 달라진다.
        List<Entry<Integer, String>> result2 = numbers2.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .toList();
        System.out.println("result2 = " + result2);
        // result2 = [1=one, 2=two, 3=three]
    }
}
