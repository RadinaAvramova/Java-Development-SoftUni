package restaurant.repositories;

import restaurant.models.client.Client;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClientRepository implements Repository<Client> {

    private final Map<String, Client> clients;

    public ClientRepository() {
        this.clients = new LinkedHashMap<>();
    }


    @Override
    public Collection<Client> getCollection() {
        return Collections.unmodifiableCollection(this.clients.values());
    }

    @Override
    public void add(Client client) {
        clients.put(client.getName(), client);
    }

    @Override
    public boolean remove(Client client) {
        return clients.remove(client.getName()) != null;
    }

    @Override
    public Client byName(String name) {
        return clients.get(name);
    }
}

