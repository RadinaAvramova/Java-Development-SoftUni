package fishingPond;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Pond {

    private final List<Fish> fishes;
    private final int capacity;

    public Pond(int capacity) {
        this.fishes = new ArrayList<>();
        this.capacity = capacity;
    }

    public void addFish(Fish fish) {
        if (fishes.size() < capacity) {
            fishes.add(fish);
        }
    }

    public boolean removeFish(String species) {
        Fish fish = this.getFish(species);
        fishes.remove(fish);
        return fish != null;
    }

    public Fish getOldestFish() {
        return fishes.stream().max(Comparator.comparing(Fish::getAge)).orElse(null);

//        Optional<Fish> oldestFish = fishes.stream()
//                .min(Comparator.comparingInt(Fish::getAge));
//        return oldestFish.orElse(null);
    }

    public Fish getFish(String species) {
        return fishes.stream().filter(f -> f.getSpecies().equals(species)).findFirst().orElse(null);
    }
    public int getCount(){
        return fishes.size();
    }

    public int getVacancies() {return capacity - getCount();}

    public String report(){

        StringBuilder build = new StringBuilder();
        build.append("Fishes in the pond:");
        build.append(System.lineSeparator());
        fishes.forEach(fish -> build.append(fish.toString()).append(String.format(System.lineSeparator())));

        return build.toString().trim();
    }
}
