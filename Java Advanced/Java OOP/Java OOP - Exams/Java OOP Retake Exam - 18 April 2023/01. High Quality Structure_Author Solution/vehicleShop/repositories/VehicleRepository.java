package vehicleShop.repositories;

import vehicleShop.models.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VehicleRepository<T> implements Repository<Vehicle> {
    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public Collection<Vehicle> getModels() {
        return Collections.unmodifiableList(this.vehicles);
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }

    @Override
    public Vehicle findByName(String name) {
        return this.vehicles.stream().filter(present -> present.getName().equals(name)).findFirst().orElse(null);
    }
}
