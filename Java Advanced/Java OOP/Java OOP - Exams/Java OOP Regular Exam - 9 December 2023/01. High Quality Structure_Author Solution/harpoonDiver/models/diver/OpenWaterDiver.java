package harpoonDiver.models.diver;

public class OpenWaterDiver extends BaseDiver{

    private static final double INITIAL_DIVER_OXYGEN = 30;
    public OpenWaterDiver(String name) {
        super(name, INITIAL_DIVER_OXYGEN);
    }
}
