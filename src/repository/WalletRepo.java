package repository;

import model.Wallet;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class WalletRepo {
    Map<String, Wallet> walletMap = new LinkedHashMap<>();

    public void addWallet(Wallet wallet) throws Exception {
        if (walletMap.containsKey(wallet.getId()))
            throw new Exception("Wallet already created");
        walletMap.put(wallet.getId(), wallet);
    }

    public void updateWallet(Wallet wallet) throws Exception {
        if (!walletMap.containsKey(wallet.getId()))
            throw new Exception("Wallet not created");
        walletMap.put(wallet.getId(), wallet);
    }

    public Optional<Wallet> getWallet(String id) {
        if (!walletMap.containsKey(id))
            return Optional.empty();
        return Optional.of(walletMap.get(id));
    }

    public Map<String, Wallet> getWalletMap() {
        return walletMap;
    }
}
