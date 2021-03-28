package model;

import service.AccountService;

import java.util.Objects;

public class OfferOne implements Offer {

    String offerId = "offerOne";

    public OfferOne() {}

    @Override
    public boolean applyOffer(AccountService accountService, Transaction transaction) {
//        User src = accountService.getUser(transaction.getSrcUser());
//        User dest = accountService.getUser(transaction.getSrcUser());
//        if (src.getWallet().getFkMoney().getAmount() != dest.getWallet().getFkMoney().getAmount())
//            return false;
//        FkMoney srcMoney = src.getWallet().getFkMoney();
//        FkMoney destMoney = dest.getWallet().getFkMoney();
//        srcMoney.setAmount(srcMoney.getAmount()+10);
//        destMoney.setAmount(destMoney.getAmount()+10);
//        try {
//            accountService.walletRepo.updateWallet(src.getWallet());
//            accountService.walletRepo.updateWallet(dest.getWallet());
//            return true;
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//            return false;
//        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferOne offerOne = (OfferOne) o;
        return Objects.equals(offerId, offerOne.offerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId);
    }
}
