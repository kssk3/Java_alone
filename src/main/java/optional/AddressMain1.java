package optional;

import optional.model.Address;
import optional.model.User;

public class AddressMain1 {

    public static void main(String[] args) {
        User user1 = new User("user1", null);
        User user2 = new User("user2", new Address("21231-546565-46545"));

        printUserStreet(user1);
        printUserStreet(user2);
    }

    private static void printUserStreet(User user) {
        String userAddress = getUserAddress(user);
        if(userAddress != null) {
            System.out.println(userAddress);
        }else{
            System.out.println("UnKnown User");
        }
    }

    static String getUserAddress(User user) {
        if (user == null) {
            return "유저 정보가 없습니다.";
        }

        Address address = user.getAddress();
        if(address == null) {
            return "유저 주소가 없습니다.";
        }

        return address.getStreet();
    }
}
