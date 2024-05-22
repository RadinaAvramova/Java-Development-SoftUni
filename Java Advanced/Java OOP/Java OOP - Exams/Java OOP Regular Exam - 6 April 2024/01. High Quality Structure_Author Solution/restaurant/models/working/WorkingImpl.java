package restaurant.models.working;

import restaurant.core.ControllerImpl;
import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;

import java.util.Collection;

public class WorkingImpl implements Working {
    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {

        Collection<String> orders = client.getClientOrders();
        Collection<String> clientServingWaiters = client.getWaiters(); //additional

        for (Waiter waiter : waiters) {
            while (waiter.canWork() && orders.iterator().hasNext()) {
//                if (!clientServingWaiters.contains(waiter.getName())) { //additional
//                    clientServingWaiters.add(waiter.getName());         //additional
//                }
                waiter.work();

                String currentOrder = orders.iterator().next();
                waiter.getOrders().getOrdersList().add(currentOrder);
                orders.remove(currentOrder);
//                ControllerImpl.orders.remove(0);
            }
        }
    }
}
