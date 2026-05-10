package service;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import model.Order;

public final class OrderQueue {

    /*
     Volatile garantează ca toate thread-urile
     vad cea mai recentă valoare a variabilei instance.

     Este important pentru implementarea thread-safe
     a Singleton-ului cu Double Checked Locking.
    */
    private static volatile OrderQueue instance;

    /*
     PriorityBlockingQueue:
     - thread-safe
     - comenzile sunt procesate după prioritate
     - comenzile cu prioritate mai mare sunt procesate primele
    */
    private final BlockingQueue<Order> queue = new PriorityBlockingQueue<>(
            10,
            Comparator.comparingInt(Order::getPriority).reversed()
    );

    /*
     Constructor privat:
     - nimeni nu poate crea obiecte cu new
     - obligatoriu pentru Singleton
    */
    private OrderQueue() {
    }

    public static OrderQueue getInstance() {

        // Prima verificare evita sincronizarea inutila după ce instanta a fost deja creata.
        if (instance == null) {

            /*
             synchronized pe clasa:
             - doar un thread intra simultan
             - this NU poate fi folosit aici
               deoarece metoda este static
            */
            synchronized (OrderQueue.class) {

                // A doua verificare este necesara deoarece alt thread poate crea instanta intre timp.
                if (instance == null) {
                    instance = new OrderQueue();
                }
            }
        }

        return instance;
    }

    public void addOrder(Order order) {
        queue.add(order);
        System.out.println("Comanda adaugata in coada: " + order);
    }

    /*
     take() blochează thread-ul pânâ apare o comanda.

     Avantaj:
     - nu consumam CPU inutil prin polling continuu.
    */
    public Order takeOrder() throws InterruptedException {
        return queue.take();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
