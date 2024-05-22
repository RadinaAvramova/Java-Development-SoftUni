package vehicleShop.models.shop;

import vehicleShop.models.worker.Worker;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;

public class ShopImpl implements Shop {
    public void make(Vehicle vehicle, Worker worker) {
        if (worker.canWork()) {
            for (Tool tool : worker.getTools()) {
                while (!tool.isUnfit()) {
                    if (!tool.isUnfit() && worker.canWork()) {
                        vehicle.making();
                        worker.working();
                        tool.decreasesPower();
                    }
                    if (vehicle.reached()) {
                        return;
                    }
                    if (!worker.canWork()) {
                        return;
                    }
                }
            }
        }
    }
}
