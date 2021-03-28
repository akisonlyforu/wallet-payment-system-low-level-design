package service;

import model.Offer;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

    List<Offer> offers;
    AccountService accountService;

    public OfferService(AccountService accountService) {
        this.offers = new ArrayList<>();
        this.accountService = accountService;
    }

    public void registerOffer(Offer offer) {
        this.offers.add(offer);
    }

    public void unregisterOffer(Offer offer) {
        this.offers.remove(offer);
    }

    void applyOffers(Transaction transaction) {
        for (Offer offer: offers)
            offer.applyOffer(accountService, transaction);
    }
}
