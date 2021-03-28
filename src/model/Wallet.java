package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Wallet {

    String id;
    String userId;
    FkMoney fkMoney;
    LocalDate localDate;

    public Wallet(String userId, FkMoney fkMoney) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.fkMoney = fkMoney;
        this.localDate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public FkMoney getFkMoney() {
        return fkMoney;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
