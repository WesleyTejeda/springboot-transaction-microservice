package com.microservice.transaction.Wallet;

import com.microservice.transaction.Transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public List<Wallet> getAllWallets(){
        return walletRepository.findAll();
    }

    public Optional<Wallet> getUserWallet(String customerId){
        return walletRepository.findWalletByCustomerId(customerId);
    }

    public Wallet createWallet(String customerId){
        Wallet wallet = new Wallet(customerId, "USD", 0f);
        walletRepository.insert(wallet);
        return wallet;
    }

    public void chargeOneWallet(Transaction transaction) {
        walletRepository.findWalletByCustomerId(transaction.getCustomerId())
                .ifPresentOrElse(wallet -> {
                    wallet.setCurrencyAmount(wallet.getCurrencyAmount() - transaction.getAmount());
                    walletRepository.save(wallet);
                    System.out.println("Updated wallet: " + wallet);
                }, () -> {
                    System.out.println("Could not find wallet to update");
                });
    }
    public void depositOneWallet(Transaction transaction) {
        walletRepository.findWalletByCustomerId(transaction.getCustomerId())
                .ifPresentOrElse(wallet -> {
                    wallet.setCurrencyAmount(wallet.getCurrencyAmount() + transaction.getAmount());
                    walletRepository.save(wallet);
                    System.out.println("Updated wallet: " + wallet);
                }, () -> {
                    System.out.println("Could not find wallet to update");
                });
    }
}
