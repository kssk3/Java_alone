package java_ad3.defaultMethod.ex1;

import java.util.List;

public class NotifierMain1 {
    public static void main(String[] args) {
        List<Notifier> notifiers = List.of(new EmailNotifier(), new SMSNotifier(), new AppNotifier());
        notifiers.forEach(n -> n.notify("서비스 가입을 환영합니다!"));
    }
}
