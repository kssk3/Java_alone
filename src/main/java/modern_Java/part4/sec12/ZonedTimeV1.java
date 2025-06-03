package modern_Java.part4.sec12;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZonedTimeV1 {

    public static void main(String[] args) {
        ZoneOffset zonedFrom = ZoneOffset.from(ZonedDateTime.now());
        System.out.println("zonedFrom = " + zonedFrom);// zonedFrom = +09:00


    }
}
