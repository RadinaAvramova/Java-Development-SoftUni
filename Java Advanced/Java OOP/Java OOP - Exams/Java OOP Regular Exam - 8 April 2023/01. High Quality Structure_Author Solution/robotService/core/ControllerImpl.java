package robotService.core;

import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Map<String, Service> services;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) { //todo WRONG SIGNATURE
        Service service;
        switch (type) {
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            case "MainService":
                service = new MainService(name);
                break;
            default:
                throw new NullPointerException(INVALID_SERVICE_TYPE);
        }

        services.put(name, service);

        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        this.supplementRepository.addSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }
    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplementForService = this.supplementRepository.findFirst(supplementType);

        if (supplementForService == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        Service service = this.services.get(serviceName);
        service.addSupplement(supplementForService);
        this.supplementRepository.removeSupplement(supplementForService);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        switch (robotType) {
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }
        Service service = this.services.get(serviceName);

        String output;

        if (!isSuitableService(robotType, service)) {
            output = UNSUITABLE_SERVICE;
        } else {
            service.addRobot(robot);
            output = String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
        }

        return output;
    }

    private boolean isSuitableService(String robotType, Service service) {
        String serviceType = service.getClass().getSimpleName();

        if (robotType.equals("FemaleRobot") && serviceType.equals("SecondaryService")) {
            return true;
        } else if (robotType.equals("MaleRobot") && serviceType.equals("MainService")) {
            return true;
        }

        return false;
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = this.services.get(serviceName);
        service.feeding();

        return String.format(FEEDING_ROBOT, service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = this.services.get(serviceName);

        double robotPrices = service.getRobots().stream()
                .mapToDouble(Robot::getPrice).sum();
        double supplementPrices = service.getSupplements().stream()
                .mapToDouble(Supplement::getPrice).sum();

        return String.format(VALUE_SERVICE, serviceName, robotPrices + supplementPrices);
    }

    @Override
    public String getStatistics() {
        return services.values()
                .stream()
                .map(Service::getStatistics)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
