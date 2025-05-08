package java_ad3.functional;

import java.util.List;

public class ImmutableMain2 {

    public static void main(String[] args) {
        MutablePerson m1 = new MutablePerson("Kim", 20);
        MutablePerson m2 = new MutablePerson("Mike", 20);

        List<MutablePerson> originList = List.of(m1, m2);
        System.out.println("실행 전");
        System.out.println("originList = " + originList);
        List<MutablePerson> list = originList.stream()
                .map(n -> {
                    n.setAge(n.getAge() + 1);
                    return n;
                })
                .toList();
        System.out.println("실행 후");
        System.out.println("originList = " + originList);
        System.out.println("list = " + list);
    }
}
