package java_ad3.optional;

import java.util.Optional;
import java_ad3.optional.model.Address;
import java_ad3.optional.model.User;

public class AddressMain2 {

    public static void main(String[] args) {
        User user1 = new User("user1", null);
        User user2 = new User("user2", new Address("21231-546565-46545"));

        printUserStreet(user1);
        printUserStreet(user2);
    }

    private static void printUserStreet(User user) {
        getUserAddress(user)
                .ifPresentOrElse(
                        n -> System.out.println(n)
                        , () -> System.out.println("UnKnown User")
                );
    }

    static Optional<String> getUserAddress(User user) {
        return Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getStreet);

    }
}
