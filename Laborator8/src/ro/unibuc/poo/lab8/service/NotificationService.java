package ro.unibuc.poo.lab8.service;

import ro.unibuc.poo.lab8.functional.NotificationSender;

public class NotificationService {

    public void notifyUser(String message, NotificationSender sender) {
        sender.send(message);
    }

    public static void printNotification(String message) {
        System.out.println("[NOTIFICATION] " + message);
    }
}