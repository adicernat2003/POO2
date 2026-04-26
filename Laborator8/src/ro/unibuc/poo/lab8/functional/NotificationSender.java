package ro.unibuc.poo.lab8.functional;

@FunctionalInterface
public interface NotificationSender {

    void send(String message);
}