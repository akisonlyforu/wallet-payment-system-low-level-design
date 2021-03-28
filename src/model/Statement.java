package model;

import utils.TransactionType;

import java.util.*;

public class Statement {

    String userId;
    String walletId;
    Double currMoney;
    Map<Transaction, TransactionType> transactionMap;

    public Statement(String userId, String walletId, Double currMoney, Map<Transaction, TransactionType> transactionMap) {
        this.userId = userId;
        this.walletId = walletId;
        this.currMoney = currMoney;
        this.transactionMap = transactionMap;
    }

    public String getUserId() {
        return userId;
    }

    public String getWalletId() {
        return walletId;
    }

    public Double getCurrMoney() {
        return currMoney;
    }

    public Map<Transaction, TransactionType> getTransactionMap() {
        return transactionMap;
    }
}
