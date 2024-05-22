package sanctuary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Habitat {
    private List<Elephant> data;
    private int capacity;

    public Habitat(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }
    public void add(Elephant elephant) {
        if (this.data.size() < this.capacity) {
            this.data.add(elephant);
        }
    }
    public boolean remove(String name) {
        for (Elephant elephant: this.data) {
            if (elephant.getName().equals(name)){
                this.data.remove(elephant);
                return true;
            }
        }
        return false;
    }
    public Elephant getElephant(String retiredFrom){
        for (Elephant elephant : this.data) {
            if (elephant.getRetiredFrom().equals(retiredFrom)) {
                return elephant;
            }
        }
        return null;
    }
    public Elephant getOldestElephant(){
        return this.data.stream().max(Comparator.comparing(Elephant::getAge)).get();
    }
    public int getAllElephants(){
        return this.data.size();
    }
    public String getReport(){
        StringBuilder sB = new StringBuilder();
        sB.append("Saved elephants in the park:").append(System.lineSeparator());
        for (Elephant elephant : this.data) {
            sB.append(String.format("%s came from: %s", elephant.getName(), elephant.getRetiredFrom()))
                    .append(System.lineSeparator());
        }
        return sB.toString().trim();
    }
}
