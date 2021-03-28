package service;

import model.Statement;
import model.Transaction;
import model.User;
import repository.TransactionRepo;
import repository.UserRepo;
import repository.WalletRepo;
import utils.TransactionType;

import java.util.*;

public class StatementService {

    TransactionRepo transactionRepo;
    UserRepo userRepo;
    WalletRepo walletRepo;

    public StatementService(TransactionRepo transactionRepo, UserRepo userRepo, WalletRepo walletRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
    }

    public Statement getStatement(String userId) {
        Optional<User> user = userRepo.getUser(userId);
        try {
            if (user.isEmpty() || user.get().getWallet() == null)
                throw new Exception("User or Wallet does not exist");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        Map<Transaction, TransactionType> transactionMapForStatement = new HashMap<>();
        for (String transactionId: transactionRepo.getUserTransactionMap().get(userId)) {
            Transaction transaction = transactionRepo.getTransactionMap().get(transactionId);
            if (transaction.getDestUser().equals(userId)) {
                transactionMapForStatement.put(transaction, TransactionType.CREDIT);
            }
            else if (transaction.getSrcUser().equals(userId)) {
                transactionMapForStatement.put(transaction, TransactionType.DEBIT);
            }
        }
        String walletId = user.get().getWallet();
        double amount = walletRepo.getWalletMap().get(user.get().getWallet()).getFkMoney().getAmount();
        System.out.println(userId + " with wallet: " + walletId + " has: " + amount);
        transactionMapForStatement.forEach((k, v) -> {
            System.out.println("Src: " + k.getSrcUser() + " Dest: " + k.getDestUser() + " Amount: " + k.getAmount().getAmount() + " Type: " + v.toString());
        });
        System.out.println();
        return new Statement(userId, walletId, amount, transactionMapForStatement);
    }
}
