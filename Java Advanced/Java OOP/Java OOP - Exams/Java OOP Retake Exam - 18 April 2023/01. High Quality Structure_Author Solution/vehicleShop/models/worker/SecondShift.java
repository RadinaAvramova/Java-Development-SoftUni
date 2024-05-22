package vehicleShop.models.worker;

public class SecondShift extends BaseWorker {
    private int energy;
    public SecondShift(String name) {
        super(name, 70);
    }

    @Override
    public void working() {
        if (this.energy - 5 < 0) {
            this.energy = 0;
        } else {
            this.energy -= 5;
        }
    }
}
