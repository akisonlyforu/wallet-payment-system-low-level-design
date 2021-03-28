package model;

import service.AccountService;

public interface Offer {

    boolean applyOffer(AccountService accountService, Transaction transaction);
}
