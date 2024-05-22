package vehicleShop.repositories;

import vehicleShop.models.worker.Worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WorkerRepository<T> implements Repository<Worker> {
    private List<Worker> workers = new ArrayList<>();

    public Collection<Worker> getModels() {
        return Collections.unmodifiableList(this.workers);
    }

    public void add(Worker worker) {
        this.workers.add(worker);
    }

    public boolean remove(Worker worker) {
        return this.workers.remove(worker);
    }

    public Worker findByName(final String name) {
        return this.workers.stream().filter(helper -> helper.getName().equals(name)).findFirst().orElse(null);
    }
}
