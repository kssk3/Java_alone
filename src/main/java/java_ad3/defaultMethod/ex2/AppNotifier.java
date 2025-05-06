package java_ad3.defaultMethod.ex2;

public class AppNotifier implements Notifier {
    @Override
    public void notify(String message) {
        System.out.println("[App] " + message);
    }
}
