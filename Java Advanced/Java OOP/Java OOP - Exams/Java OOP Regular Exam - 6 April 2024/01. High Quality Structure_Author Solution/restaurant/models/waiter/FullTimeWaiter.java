package restaurant.models.waiter;


import restaurant.core.ControllerImpl;

public class FullTimeWaiter extends BaseWaiter {

    private static final int INITIAL_EFFICIENCY = 8;
    public FullTimeWaiter(String name) {
        super(name, INITIAL_EFFICIENCY);
    }

    @Override
    public void work() {

//        this.setEfficiency(this.getEfficiency() - Integer.parseInt(ControllerImpl.orders.get(0)));
        this.setEfficiency(this.getEfficiency() - 1);

    }

}
