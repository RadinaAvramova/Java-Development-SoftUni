package dolphinarium.repositories;

import dolphinarium.entities.foods.Food;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class FoodRepositoryImpl implements FoodRepository{

    private Collection<Food> foods;

    public FoodRepositoryImpl() {
        this.foods = new ArrayList<>();
    }

//    private Map<Class<? extends Food>, Integer> foods;
//
//    public FoodRepositoryImpl() {
//        this.foods = new HashMap<>();
//    }

    @Override
    public void add(Food food) {
//        if (foods.contains(food)) {
//            throw new IllegalArgumentException("This type of food already exists in repository.");
//        }
        this.foods.add(food);
    }

    @Override
    public boolean remove(Food food) {
        return this.foods.remove(food);
    }

//    public void add(Food food) {
//        Class<? extends Food> foodClass = food.getClass();
//        foods.put(foodClass, foods.getOrDefault(foodClass, 0) + 1);
//    }
//
//    public void remove(Food food) {
//        Class<? extends Food> foodClass = food.getClass();
//        int count = foods.getOrDefault(foodClass, 0);
//        if (count > 0) {
//            foods.put(foodClass, count - 1);
//        }
//    }

    @Override
    public Food findByType(String type) {
        return foods.stream()
                .filter(food -> food.getClass().getSimpleName().equals(type))
                .findFirst()
                .orElse(null);
    }

//    public Food findByType(String foodType) {
//        for (Map.Entry<Class<? extends Food>, Integer> entry : foods.entrySet()) {
//            Class<? extends Food> foodClass = entry.getKey();
//            if (foodClass.getSimpleName().equalsIgnoreCase(foodType)) {
//                try {
//                    return foodClass.getDeclaredConstructor().newInstance();
//                } catch (InstantiationException | IllegalAccessException |
//                         NoSuchMethodException | InvocationTargetException e) {
//                    e.printStackTrace();
//                    // Handle the exception appropriately, e.g., log and return null
//                    return null;
//                }
//            }
//        }
//        return null;
//    }

    public int getFoodsCount() {
        return foods.size();
    }

    public Collection<Food> getFoods() {

        return foods;
    }

//    public Collection<Food> getFoods() {
//        List<Food> foodList = new ArrayList<>();
//        for (Map.Entry<Class<? extends Food>, Integer> entry : foods.entrySet()) {
//            Class<? extends Food> foodClass = entry.getKey();
//            int count = entry.getValue();
//            for (int i = 0; i < count; i++) {
//                try {
//                    Food food = foodClass.getDeclaredConstructor().newInstance();
//                    foodList.add(food);
//                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                    e.printStackTrace();
//                    // Handle the exception appropriately, e.g., log and continue
//                }
//            }
//        }
//        return foodList;
//    }
}
