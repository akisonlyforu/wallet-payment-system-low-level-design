import model.OfferOne;
import repository.TransactionRepo;
import repository.UserRepo;
import repository.WalletRepo;
import service.AccountService;
import service.OfferService;
import service.StatementService;
import service.TransactionService;


public class TestApp {

    public static void main(String[] args) {

        TransactionRepo transactionRepo = new TransactionRepo();
        UserRepo userRepo = new UserRepo();
        WalletRepo walletRepo = new WalletRepo();


        AccountService accountService = new AccountService(userRepo, walletRepo);
        OfferService offerService = new OfferService(accountService);
        StatementService statementService = new StatementService(transactionRepo, userRepo, walletRepo);
        TransactionService transactionService = new TransactionService(transactionRepo, userRepo, walletRepo, offerService);

        offerService.registerOffer(new OfferOne());

        accountService.createUser("Harry");
        accountService.createUser("Ron");
        accountService.createUser("Hermione");
        accountService.createUser("Albus");
        accountService.createUser("Draco");

        accountService.createWalletForUser("Harry", 100);
        accountService.createWalletForUser("Ron", 100);
        accountService.createWalletForUser("Hermione", 100);
        accountService.createWalletForUser("Albus", 100);
        accountService.createWalletForUser("Draco", 100);

        accountService.getOverview();

        transactionService.makeTransaction("Albus", "Draco", 30);
        transactionService.makeTransaction("Hermione", "Harry", 20);
        transactionService.makeTransaction("Albus", "Ron", 50);

        accountService.getOverview();

        statementService.getStatement("Harry");
        statementService.getStatement("Ron");
        statementService.getStatement("Hermione");
        statementService.getStatement("Albus");
        statementService.getStatement("Draco");
    }
}