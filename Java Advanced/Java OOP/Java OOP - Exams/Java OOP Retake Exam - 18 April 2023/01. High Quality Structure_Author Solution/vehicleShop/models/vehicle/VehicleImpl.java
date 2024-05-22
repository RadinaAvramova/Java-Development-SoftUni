package vehicleShop.models.vehicle;

import vehicleShop.common.ExceptionMessages;

public class VehicleImpl implements Vehicle {
    private String name;
    private int strengthRequired;

    public VehicleImpl(String name, int strengthRequired) {
        setName(name);
        setStrengthRequired(strengthRequired);
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new NullPointerException(ExceptionMessages.VEHICLE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setStrengthRequired(int strengthRequired) {
        if (strengthRequired < 0) {
            throw new IllegalArgumentException(ExceptionMessages.VEHICLE_STRENGTH_LESS_THAN_ZERO);
        }
        this.strengthRequired = strengthRequired;
    }

    public String getName() {
        return this.name;
    }

    public int getStrengthRequired() {
        return this.strengthRequired;
    }

    public boolean reached() {
        return this.strengthRequired == 0;
    }

    public void making() {
        if (this.strengthRequired - 5 < 0) {
            this.strengthRequired = 0;
        } else {
            this.strengthRequired -= 5;
        }
    }
}
