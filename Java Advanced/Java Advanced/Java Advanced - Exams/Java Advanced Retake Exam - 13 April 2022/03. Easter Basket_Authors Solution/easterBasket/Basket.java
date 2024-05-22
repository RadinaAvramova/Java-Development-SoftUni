package easterBasket;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Basket {
    private List<Egg> data;

    private String material;

    private int capacity;


    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        this.data = new ArrayList<>();

    }

    public void addEgg(Egg egg) {
        if (data.size() < capacity) {
            data.add(egg);
        }
    }

    public boolean removeEgg(String color) {
        Egg egg = this.getEgg(color);
        data.remove(egg);
        return egg != null;
    }

    public Egg getStrongestEgg(){
        return data.stream().max(Comparator.comparing(Egg::getStrength)).orElse(null);
    }

    public Egg getEgg(String color){
       return data.stream().filter(e -> e.getColor().equals(color)).findFirst().orElse(null);
    }

    public int getCount(){
        return data.size();
    }

    public String report(){

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s basket contains:%n",material));

        data.forEach(egg -> sb.append(egg.toString()).append(String.format("%n")));

        return sb.toString().trim();
    }
}
