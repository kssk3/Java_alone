package java_ad3.functional;

import java.util.List;

public class ImmutableMain3 {

    public static void main(String[] args) {
        ImmutablePerson im1 = new ImmutablePerson("Kim", 20);
        ImmutablePerson im2 = new ImmutablePerson("Mike", 20);

        List<ImmutablePerson> originList = List.of(im1, im2);
        System.out.println("=== 실행 전 ===");
        System.out.println("originList = " + originList);
        List<ImmutablePerson> list = originList.stream()
                .map(n -> n.withAge(n.getAge() + 1))
                .toList();
        System.out.println("=== 실행 후 ===");
        System.out.println("originList = " + originList);
        System.out.println("list = " + list);
    }
}
