package optional;

public class PersonMain {
    public static void main(String[] args) {

        Person person1 = new Person("Kim");
        Person person2 = new Person();

        System.out.println(person1.getName()); // Kim
        System.out.println(person2.getName()); // 입력된 이름이 없습니다.
    }
}
