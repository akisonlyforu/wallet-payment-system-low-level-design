package service;

import model.FkMoney;
import model.Transaction;
import model.User;
import model.Wallet;
import repository.TransactionRepo;
import repository.UserRepo;
import repository.WalletRepo;

import java.time.LocalDate;

public class TransactionService {

    TransactionRepo transactionRepo;
    UserRepo userRepo;
    WalletRepo walletRepo;
    OfferService offerService;
    int retryAttempt = 3;

    public TransactionService(TransactionRepo transactionRepo, UserRepo userRepo, WalletRepo walletRepo, OfferService offerService) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
        this.offerService = offerService;
    }

    public boolean makeTransaction(String srcUser, String destUser, double amountTransferred) {
        FkMoney amount = new FkMoney(amountTransferred);
        if(srcUser.equals(destUser))
            return false;
        if (userRepo.getUser(srcUser).isEmpty() || userRepo.getUser(destUser).isEmpty())
            return false;
        User src = userRepo.getUser(srcUser).get();
        User dest = userRepo.getUser(destUser).get();

        Wallet srcWallet = walletRepo.getWalletMap().get(src.getWallet());
        Wallet destWallet = walletRepo.getWalletMap().get(dest.getWallet());

        if(srcWallet == null || destWallet == null)
            return false;

        FkMoney srcMoney = srcWallet.getFkMoney();
        FkMoney destMoney = destWallet.getFkMoney();

        if (amount.getAmount() < 0.0001 || srcMoney.getAmount() < amount.getAmount())
            return false;

        FkMoney srcMoneyCopy = srcWallet.getFkMoney();
        FkMoney destMoneyCopy = destWallet.getFkMoney();

        synchronized (this) {
            try {
                srcMoney.setAmount(srcMoney.getAmount()-amount.getAmount());
                destMoney.setAmount(destMoney.getAmount()+amount.getAmount());
                Transaction transaction = null;
                int count = 0;
                while (count < retryAttempt) {
                    transaction = persistTransaction(srcUser, destUser, srcWallet, destWallet, amount);
                    if (transaction != null)
                        return true;
                    count++;
                }
                return false;
            } catch (Exception exception) {
                System.out.println(exception.getMessage());

                //case when src money deducted from wallet but failed at adding money to destination wallet
                if (destMoney.getAmount() == destMoneyCopy.getAmount()) {
                    if (srcMoney.getAmount() != srcMoneyCopy.getAmount())
                        srcMoney.setAmount(srcMoneyCopy.getAmount());
                }
                return false;
            }
        }
    }

    private Transaction persistTransaction(String srcUser, String destUser, Wallet srcWallet, Wallet destWallet, FkMoney amount) {
        try {
            Transaction transaction = new Transaction(srcUser, destUser, amount, LocalDate.now());
            transactionRepo.addTransaction(transaction);
            walletRepo.updateWallet(srcWallet);
            walletRepo.updateWallet(destWallet);
            offerService.applyOffers(transaction);
            transactionRepo.addUserTransaction(srcUser, transaction.getId());
            transactionRepo.addUserTransaction(destUser, transaction.getId());
            System.out.println("Transaction complete");
            return transaction;
        } catch (Exception exception) {
            System.out.println(exception.toString());
            return null;
        }
    }

}
