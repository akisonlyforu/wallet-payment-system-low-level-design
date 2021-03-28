package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    String id;
    String srcUser;
    String destUser;
    FkMoney amount;
    LocalDate timeStamp;

    public Transaction(String srcUser, String destUser, FkMoney amount, LocalDate timeStamp) {
        this.id = UUID.randomUUID().toString();
        this.srcUser = srcUser;
        this.destUser = destUser;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public String getSrcUser() {
        return srcUser;
    }

    public String getDestUser() {
        return destUser;
    }

    public FkMoney getAmount() {
        return amount;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
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
