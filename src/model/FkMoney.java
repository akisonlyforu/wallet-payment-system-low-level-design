package model;

public class FkMoney {
    double amount;

    public FkMoney() {
        this.amount = 0.00001;
    }

    public FkMoney(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}