package restaurant.repositories;

import restaurant.models.waiter.Waiter;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WaiterRepository implements Repository<Waiter> {

    private final Map<String, Waiter> waiters;

    public WaiterRepository() {
        this.waiters = new LinkedHashMap<>();
    }

    @Override
    public Collection<Waiter> getCollection() {
        return Collections.unmodifiableCollection(this.waiters.values());
    }

    @Override
    public void add(Waiter waiter) {
        waiters.put(waiter.getName(), waiter);
    }

    @Override
    public boolean remove(Waiter waiter) {
        return waiters.remove(waiter.getName()) != null;
    }

    @Override
    public Waiter byName(String name) {
        return waiters.get(name);
    }
}
