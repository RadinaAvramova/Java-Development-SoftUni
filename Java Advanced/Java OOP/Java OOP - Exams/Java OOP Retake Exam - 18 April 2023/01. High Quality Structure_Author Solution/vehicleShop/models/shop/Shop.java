package vehicleShop.models.shop;

import vehicleShop.models.worker.Worker;
import vehicleShop.models.vehicle.Vehicle;

public interface Shop {
    void make(Vehicle vehicle, Worker worker);
}
