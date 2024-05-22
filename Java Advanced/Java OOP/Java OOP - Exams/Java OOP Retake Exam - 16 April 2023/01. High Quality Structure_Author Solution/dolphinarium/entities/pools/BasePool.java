package dolphinarium.entities.pools;

import dolphinarium.common.ExceptionMessages;
import dolphinarium.entities.dolphins.Dolphin;
import dolphinarium.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static dolphinarium.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;
import static dolphinarium.common.ExceptionMessages.POOL_NAME_NULL_OR_EMPTY;

public abstract class BasePool implements Pool {

    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Dolphin> dolphins;

    public BasePool(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.dolphins = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(POOL_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Dolphin> getDolphins() {
        return dolphins;
    }

    @Override
    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public void addDolphin(Dolphin dolphin) {

        if (dolphins.size() >= capacity) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }
        if(dolphin.getEnergy() <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.DOLPHIN_ENERGY_BELOW_OR_EQUAL_ZERO);
        }
        this.dolphins.add(dolphin);
    }

    @Override
    public void removeDolphin(Dolphin dolphin) {

        this.dolphins.remove(dolphin);
    }

    @Override
    public void addFood(Food food) {

        this.foods.add(food);
    }

//    @Override
//    public String getInfo() { remove
//        return toString();
//    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

//    @Override
//    public String toString() { remove
//
//        StringBuilder build = new StringBuilder();
//        build.append(String.format("%s:", this.name))
//                .append(System.lineSeparator())
//                .append("Dolphins: ");
//
//        if (this.dolphins.isEmpty()) {
//            build.append("none");
//        } else {
//
//            build.append(this.dolphins.stream().map(Dolphin::getName).collect(Collectors.joining(", ")))
//                    .append(System.lineSeparator());
//        }
//        return build.toString().trim();
//    }
}
