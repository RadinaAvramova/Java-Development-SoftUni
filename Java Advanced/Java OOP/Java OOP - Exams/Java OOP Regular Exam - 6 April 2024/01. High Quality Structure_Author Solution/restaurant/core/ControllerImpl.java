package restaurant.core;

import restaurant.common.ConstantMessages;
import restaurant.common.ExceptionMessages;
import restaurant.models.client.Client;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.working.Working;
import restaurant.models.working.WorkingImpl;
import restaurant.models.client.ClientImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.WaiterRepository;
import restaurant.repositories.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private final Repository<Client> clientRepository;
    private final Repository<Waiter> waiterRepository;
    private int servedClients;

    public ControllerImpl() {
        this.clientRepository = new ClientRepository();
        this.waiterRepository = new WaiterRepository();
    }


    @Override
    public String addWaiter(String type, String waiterName) {

        Waiter waiter;
        switch (type) {
            case "HalfTimeWaiter":
                waiter = new HalfTimeWaiter(waiterName);
                break;
            case "FullTimeWaiter":
                waiter = new FullTimeWaiter(waiterName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.WAITER_INVALID_TYPE);
        }
        this.waiterRepository.add(waiter);
        return String.format(ConstantMessages.WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... clientOrders) {

        Client client = new ClientImpl(clientName);

        for (String order : clientOrders) {
            client.getClientOrders().add(order);
        }


           this.clientRepository.add(client);
        return String.format(ConstantMessages.CLIENT_ADDED, clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {

        Waiter waiter = this.waiterRepository.byName(waiterName);

        if (waiter == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.WAITER_DOES_NOT_EXIST, waiterName));
        }
        waiterRepository.remove(waiter);

        return String.format(ConstantMessages.WAITER_REMOVE, waiterName);

    }

    @Override
    public String removeClient(String clientName) {

        Client client = this.clientRepository.byName(clientName);

        if(client == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CLIENT_DOES_NOT_EXIST, clientName));
        }
        clientRepository.remove(client);

        return String.format(ConstantMessages.CLIENT_REMOVE, clientName);
    }

    @Override
    public String startWorking(String clientName) {

        List<Waiter> waiters = this.waiterRepository.getCollection().stream()
                .filter(w -> w.getEfficiency() > 0)
                .collect(Collectors.toList());

        StringBuilder build = new StringBuilder();

        if (waiters.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.THERE_ARE_NO_WAITERS);
        }
        Client client = this.clientRepository.byName(clientName);
        Working working = new WorkingImpl();
        working.takingOrders(client, waiters);
        long busy = waiters.stream().filter(w -> w.getEfficiency() == 0).count(); //removed waiters, additional
        this.servedClients++;

        build.append(String.format(ConstantMessages.ORDERS_SERVING, clientName));
//        build.append(String.join(", ", client.getWaiters())); //additional
        return build.toString();
    }

    @Override
    public String getStatistics() {
        StringBuilder build = new StringBuilder();
        build.append(String.format(ConstantMessages.FINAL_CLIENTS_COUNT, servedClients));
        build.append(System.lineSeparator());
        build.append(ConstantMessages.FINAL_WAITERS_STATISTICS);

        Collection<Waiter> waiters = waiterRepository.getCollection();
        for (Waiter waiter : waiters) {
            build.append(System.lineSeparator());
            build.append(String.format(ConstantMessages.FINAL_WAITER_NAME, waiter.getName()));
            build.append(System.lineSeparator());
            build.append(String.format(ConstantMessages.FINAL_WAITER_EFFICIENCY, waiter.getEfficiency()));
            build.append(System.lineSeparator());
            if (waiter.getOrders().getOrdersList().isEmpty()) {
                build.append(String.format(ConstantMessages.FINAL_WAITER_ORDERS, "None"));

            } else {
                build.append(String.format(ConstantMessages.FINAL_WAITER_ORDERS,
                        String.join(ConstantMessages.FINAL_WAITER_ORDERS_DELIMITER, waiter.getOrders().getOrdersList())));
            }
        }
        return build.toString();
    }
}
