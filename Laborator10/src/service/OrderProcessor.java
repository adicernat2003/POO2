package service;

import adapter.PaymentProcessor;
import model.Order;
import model.Product;
import observer.OrderObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderProcessor {
    private final OrderQueue orderQueue;
    private final PaymentProcessor paymentProcessor;
    private final List<OrderObserver> observers;

    /*
     AtomicInteger permite increment thread-safe fără synchronized.
     incrementAndGet() este atomic.
    */
    private final AtomicInteger processedOrdersCount = new AtomicInteger(0);

    /*
     ConcurrentHashMap este thread-safe.
     Mai multe thread-uri pot citi/scrie simultan fără probleme de concurenta.
    */
    private final Map<String, Order> processedOrders = new ConcurrentHashMap<>();

    private ExecutorService executorService;

    // volatile: toate thread-urile vad imediat modificările.
    private volatile boolean running = true;

    public OrderProcessor(OrderQueue orderQueue,
                          PaymentProcessor paymentProcessor,
                          List<OrderObserver> observers) {
        this.orderQueue = orderQueue;
        this.paymentProcessor = paymentProcessor;
        this.observers = observers;
    }

    public void startProcessing(int numberOfWorkers) {

        // Thread pool: reutilizează thread-urile existente in loc sa creeze thread-uri noi constant.
        executorService = Executors.newFixedThreadPool(numberOfWorkers);

        for (int i = 0; i < numberOfWorkers; i++) {
            executorService.submit(new Worker());
        }
    }

    public void stopProcessing() {

        // Așteptăm pânâ când toate comenzile sunt procesate.
        while (!orderQueue.isEmpty()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        running = false;

        // shutdownNow() întrerupe thread-urile active.
        executorService.shutdownNow();
    }

    public int getProcessedOrdersCount() {
        return processedOrdersCount.get();
    }

    public Map<String, Order> getProcessedOrders() {
        return processedOrders;
    }

    // synchronized: doar un thread poate notifica observatorii simultan.
    private synchronized void notifyObservers(Order order, String status) {
        for (OrderObserver observer : observers) {
            observer.update(order, status);
        }
    }

    /*
     Worker reprezinta un task executat concurent.

     Fiecare worker:
     - ia comenzi din coada
     - procesează plata
     - pregătește comanda
    */
    private class Worker implements Runnable {

        @Override
        public void run() {

            // Fiecare worker rulează continuu până când aplicația este oprita.
            while (running) {

                try {

                    /*
                     Thread-ul așteaptă automat pâna apare o comanda.
                     PriorityBlockingQueue sincronizează intern accesul la structură
                    */
                    Order order = orderQueue.takeOrder();

                    notifyObservers(order, "PRELUATA");

                    System.out.println("Produse pregatite pentru " + order.getCustomerName() + ":");

                    for (Product product : order.getProducts()) {
                        System.out.println("- "
                                + product.getName()
                                + " - "
                                + product.getPrice()
                                + " RON");
                    }

                    boolean paymentOk = paymentProcessor.pay(order.getTotalPrice());

                    if (!paymentOk) {
                        notifyObservers(order, "PLATA ESUATA");
                        continue;
                    }

                    notifyObservers(order, "IN PREGATIRE");

                    // Simulăm o operație lenta: prepararea produselor.
                    Thread.sleep(1000);

                    processedOrders.put(order.getCustomerName(), order);

                    // Increment atomic thread-safe.
                    processedOrdersCount.incrementAndGet();

                    notifyObservers(order, "FINALIZATA");

                } catch (InterruptedException e) {

                    // Resetam flag-ul de întrerupere.
                    Thread.currentThread().interrupt();

                    break;
                }
            }
        }
    }
}