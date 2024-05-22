package robotService.entities.services;

import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT;
import static robotService.common.ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY;

public abstract class BaseService implements Service {

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    protected BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.getRobots().size() < this.capacity) {
            this.robots.add(robot);
            return;
        }

        throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
    }

    @Override
    public void removeRobot(Robot robot) {
        this.getRobots().remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.getSupplements().add(supplement);
    }

    @Override
    public void feeding() {
        for (Robot robot : this.getRobots()) {
            robot.eating();
        }
    }

    @Override
    public int sumHardness() {
        int sum = 0;

        for (Supplement supplement : this.getSupplements()) {
            sum += supplement.getHardness();
        }

        return sum;
    }

    @Override
    public String getStatistics() {
        return String.format("%s %s:%n", this.name, this.getClass().getSimpleName())
                + String.format("Robots: %s%n", getRobots().isEmpty()
                ? "none"
                : this.getRobots().stream().map(Robot::getName).collect(Collectors.joining(" ")).trim())
                + String.format("Supplements: %s Hardness: %s%n", this.getSupplements().size(), this.sumHardness());
    }
}
