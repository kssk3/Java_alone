package java_ad3.parallel;

import java_ad3.Util.MyLogger;

public class HeavyJob {

    public static int heavyJob(int i) {
        MyLogger.log("calculate " + i + " -> " + i * 10);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i * 10;
    }

    public static int heavyJob(int i, String name) {
        MyLogger.log(" [" + name + "] " + i + " -> " + i * 10);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i * 10;
    }
}
