package java_ad3.functional;

public class ImmutableMain1 {

    public static void main(String[] args) {
        MutablePerson m1 = new MutablePerson("Kim", 20);
        MutablePerson m2 = m1;
        m2.setAge(21);

        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);

        ImmutablePerson im1 = new ImmutablePerson("Lim", 20);
        ImmutablePerson im2 = im1.withAge(22);

        System.out.println("im1 = " + im1);
        System.out.println("im2 = " + im2);
    }
}
