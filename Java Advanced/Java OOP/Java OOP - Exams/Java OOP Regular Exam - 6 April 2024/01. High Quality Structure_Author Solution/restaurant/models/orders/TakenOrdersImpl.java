package restaurant.models.orders;

import java.util.ArrayList;
import java.util.Collection;

public class TakenOrdersImpl implements TakenOrders {

    private Collection<String> takenOrders;

    public TakenOrdersImpl() {
        this.takenOrders = new ArrayList<>();
    }

    @Override
    public Collection<String> getOrdersList() {
        return takenOrders;
    }
}
