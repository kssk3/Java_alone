package java_ad3.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class MyLogger {

    private static final DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object object) {
        String time = LocalDateTime.now().format(formater);
        System.out.printf("%s [%9s] %s\n", time, Thread.currentThread().getName(), object);
    }


}
