package bank.entities.client;

import static bank.common.ExceptionMessages.*;

public abstract class BaseClient implements Client {
    private String name;
    private String ID;
    private int interest;
    private double income;

    protected BaseClient(String name, String ID, int interest, double income) {
        this.setName(name);
        this.setID(ID);
        this.setIncome(income);
        this.setInterest(interest);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(CLIENT_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        if (ID == null || ID.trim().isEmpty()) {
            throw new NullPointerException(CLIENT_ID_CANNOT_BE_NULL_OR_EMPTY);
        }

        this.ID = ID;
    }

    @Override
    public int getInterest() {
        return this.interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    @Override
    public double getIncome() {
        return this.income;
    }

    public void setIncome(double income) {
        if (income <= 0) {
            throw new IllegalArgumentException(CLIENT_INCOME_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        }

        this.income = income;
    }
}
