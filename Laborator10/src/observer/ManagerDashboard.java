package observer;

import model.Order;

public class ManagerDashboard implements OrderObserver {
    @Override
    public void update(Order order, String status) {
        System.out.println("[ManagerDashboard] Comanda " + order + " are statusul: " + status);
    }
}