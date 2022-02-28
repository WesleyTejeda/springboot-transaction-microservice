package com.microservice.transaction.Transaction;

import com.microservice.transaction.Wallet.WalletRepository;
import com.microservice.transaction.Wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="transactions")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final WalletRepository walletRepository;
    private final WalletService walletService;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository, TransactionService transactionService, WalletRepository walletRepository, WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
        this.walletRepository = walletRepository;
        this.walletService = walletService;
    }
    @GetMapping("")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("{CustomerId}")
    public Optional<List<Transaction>> getUserTransaction(@PathVariable("customerId") String customerId) {
        return transactionService.getUserTransactions(customerId);
    }

    @PostMapping(path="create")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        if(transaction.getType().equals("deposit")){
            transactionService.createTransaction(transaction);
            return transaction;
        } else if (transaction.getType().equals("purchase")){
            transaction.setAmount(transaction.getQuantity() * transaction.getPricePerUnit());
            transaction.setQuantityAvailable(transaction.getQuantity());
        }
        transactionService.createTransaction(transaction);
        return transaction;
    }
    @PostMapping(path="sell")
    public void sellFund(@RequestBody Transaction incoming){
        transactionService.sellFund(incoming.getId(), incoming);
    }
}