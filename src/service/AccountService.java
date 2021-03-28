package service;

import model.FkMoney;
import model.User;
import model.Wallet;
import repository.UserRepo;
import repository.WalletRepo;

public class AccountService {

    UserRepo userRepo;
    public WalletRepo walletRepo;

    public AccountService(UserRepo userRepo, WalletRepo walletRepo) {
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
    }

    public boolean createUser(String id) {
        try {
            userRepo.addUser(new User(id));
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public User getUser(String id) {
        return userRepo.getUser(id).get();
    }

    public boolean createWalletForUser(String userId, double amount) {
        if(userRepo.getUser(userId).get().getWallet() != null)
            return false;
        try {
            Wallet newWallet = new Wallet(userId, new FkMoney(amount));
            walletRepo.addWallet(newWallet);
            userRepo.getUser(userId).get().setWallet(newWallet.getId());
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public void getOverview() {
        for (Wallet wallet : walletRepo.getWalletMap().values())
            System.out.println(wallet.getUserId() + " Has " + wallet.getFkMoney().getAmount());
        System.out.println();
    }

    public boolean createWalletForUser(String userId) {
        return createWalletForUser(userId, 0.0001);
    }

}
