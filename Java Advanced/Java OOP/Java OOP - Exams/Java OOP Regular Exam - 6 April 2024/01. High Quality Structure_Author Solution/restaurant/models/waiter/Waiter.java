package restaurant.models.waiter;

import restaurant.models.orders.TakenOrders;

public interface Waiter {
    String getName();

    int getEfficiency();

    boolean canWork();

    TakenOrders getOrders();


    void work();
}
