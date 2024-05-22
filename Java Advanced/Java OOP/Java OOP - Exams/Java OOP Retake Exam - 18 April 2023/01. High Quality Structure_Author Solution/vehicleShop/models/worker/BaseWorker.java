package vehicleShop.models.worker;

import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.tool.Tool;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseWorker implements Worker {
    private String name;
    private int strength;
    private Collection<Tool> tools = new ArrayList<>();

    public BaseWorker(String name, int strength) {
        setName(name);
        setStrength(strength);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new NullPointerException(ExceptionMessages.WORKER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public Collection<Tool> getTools() {
        return tools;
    }

    public int getStrength() {
        return strength;
    }

    private void setStrength(int strength) {
        if (strength < 0) {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    public void working() {
        if (this.strength - 10 < 0) {
            this.strength = 0;
        } else {
            this.strength -= 10;
        }
    }

    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    public boolean canWork() {
        return !(this.strength == 0);
    }
}
