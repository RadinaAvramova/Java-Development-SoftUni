package entities.food;

public class Meat extends BaseFood{
    private final static int CALORIES = 70;
    private final static double PRICE = 10;

    public Meat() {
        super(CALORIES, PRICE);
    }
}
