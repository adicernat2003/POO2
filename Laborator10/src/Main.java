import java.util.List;

import adapter.LegacyPaymentAdapter;
import factory.CoffeeShopFactory;
import factory.FastCoffeeShopFactory;
import legacy.LegacyPaymentSystem;
import model.Order;
import model.Product;
import observer.KitchenDisplay;
import observer.ManagerDashboard;
import service.OrderProcessor;
import service.OrderQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CoffeeShopFactory factory = new FastCoffeeShopFactory();

        System.out.println("Ambalaj folosit de cafenea: " + factory.createPackaging());

        Product espresso = factory.createProduct("espresso");
        Product latte = factory.createProduct("latte");

        Order order1 = new Order.Builder("Maria")
                .addProduct(espresso)
                .addProduct(latte)
                .priority(2)
                .build();

        Order order2 = new Order.Builder("Andrei")
                .addProduct(factory.createProduct("tea"))
                .priority(1)
                .build();

        Order order3 = new Order.Builder("Ioana")
                .addProduct(factory.createProduct("espresso"))
                .priority(5)
                .build();

        OrderQueue queue = OrderQueue.getInstance();

        KitchenDisplay kitchenDisplay = new KitchenDisplay();
        ManagerDashboard managerDashboard = new ManagerDashboard();

        OrderProcessor processor = new OrderProcessor(
                queue,
                new LegacyPaymentAdapter(new LegacyPaymentSystem()),
                List.of(kitchenDisplay, managerDashboard)
        );

        Thread producerThread = new Thread(() -> {
            queue.addOrder(order1);
            queue.addOrder(order2);
            queue.addOrder(order3);
        }, "Producer-Thread");

        producerThread.start();

        // thread-ul principal așteaptă terminarea lui producerThread.
        producerThread.join();

        processor.startProcessing(3);

        Thread.sleep(5000);

        processor.stopProcessing();

        System.out.println("\nStatistici finale:");
        System.out.println("Comenzi procesate: " + processor.getProcessedOrdersCount());
        System.out.println("Comenzi salvate in istoric: " + processor.getProcessedOrders());
    }
}
