package observer;

import model.Order;

public class KitchenDisplay implements OrderObserver {
    @Override
    public void update(Order order, String status) {
        System.out.println("[KitchenDisplay] " + order.getCustomerName() + ": " + status);
    }
}