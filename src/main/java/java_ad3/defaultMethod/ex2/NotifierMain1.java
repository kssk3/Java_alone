package java_ad3.defaultMethod.ex2;

import java.time.LocalDateTime;
import java.util.List;

public class NotifierMain1 {
    public static void main(String[] args) {
        List<Notifier> notifiers = List.of(new EmailNotifier(), new SMSNotifier(), new AppNotifier());
        notifiers.forEach(n -> n.notify("서비스 가입을 환영합니다!"));

        LocalDateTime plus1Days = LocalDateTime.now().plusDays(1);
        notifiers.forEach(n -> n.scheduleNotification("Hello!",plus1Days));
    }
}
