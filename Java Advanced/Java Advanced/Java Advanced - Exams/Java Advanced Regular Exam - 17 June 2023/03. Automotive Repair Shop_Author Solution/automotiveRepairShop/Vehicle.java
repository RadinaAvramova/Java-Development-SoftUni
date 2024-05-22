package automotiveRepairShop;

public class Vehicle {

    private String VIN;
    private int mileage;
    private String damage;

    public int getMileage() {
        return mileage;
    }

    public String getDamage() {
        return damage;
    }

    public String getVIN() {
        return VIN;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Vehicle(String vin, int mileage, String damage) {
        this.VIN = vin;
        this.mileage = mileage;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return String.format("Damage: %s, Vehicle: %s (%d km)", damage, VIN, mileage);
    }
}
