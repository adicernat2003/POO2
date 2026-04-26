package ro.unibuc.poo.lab8.demo;

import ro.unibuc.poo.lab8.functional.NotificationSender;

public class AnonymousClassesDemo {

    public static void run() {
        System.out.println("\n--- Anonymous Classes Demo ---");

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task executat prin clasa anonima.");
            }
        };

        task.run();

        NotificationSender emailSender = new NotificationSender() {
            @Override
            public void send(String message) {
                System.out.println("EMAIL: " + message);
            }
        };

        emailSender.send("Ai primit o nota noua.");
    }
}