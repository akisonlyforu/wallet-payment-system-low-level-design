package repository;

import model.Transaction;

import java.util.*;

public class TransactionRepo {

    Map<String, Transaction> transactionMap = new LinkedHashMap<>();
    Map<String, Set<String>> userTransactionMap = new LinkedHashMap<>();

    public void addTransaction(Transaction transaction) throws Exception {
        if (transactionMap.containsKey(transaction.getId()))
            throw new Exception("Transaction already exists");
        transactionMap.put(transaction.getId(), transaction);
    }

    public Map<String, Transaction> getTransactionMap() {
        return transactionMap;
    }

    public Map<String, Set<String>> getUserTransactionMap() {
        return userTransactionMap;
    }

    public void addUserTransaction(String userId, String transactionId) throws Exception {
        if (userTransactionMap.getOrDefault(userId, new HashSet<>()).contains(transactionId))
            throw new Exception("User has a identical transaction Id");
        Set<String> transactionsByUser = userTransactionMap.getOrDefault(userId, new HashSet<>());
        transactionsByUser.add(transactionId);
        userTransactionMap.put(userId, transactionsByUser);
    }
}
