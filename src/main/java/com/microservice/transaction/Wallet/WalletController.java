package com.microservice.transaction.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "wallets")
public class WalletController {
    private final WalletService walletService;
    private final WalletRepository walletRepository;

    @Autowired
    public WalletController(WalletService walletService, WalletRepository walletRepository) {
        this.walletService = walletService;
        this.walletRepository = walletRepository;
    }

    @GetMapping(path = "find/all")
    public List<Wallet> getAllWallets(){
        return walletService.getAllWallets();
    }

    @GetMapping("find/{customerId}")
    public Optional<Wallet> getUserWallet(@PathVariable("customerId") String customerId) {
        return walletService.getUserWallet(customerId);
    }
}
