package com.microservice.transaction.Transaction;

import com.microservice.transaction.Wallet.WalletService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;

    public TransactionService(TransactionRepository transactionRepository, WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.walletService = walletService;
    }

    public Optional<List<Transaction>> getUserTransactions(String customerId) {
        return transactionRepository.findAllTransactionByCustomerId(customerId);
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.insert(transaction);
        if(transaction.getType().equals("deposit")){
            walletService.depositOneWallet(transaction);
        } else if(transaction.getType().equals("purchase")) {
            walletService.chargeOneWallet(transaction);
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void sellFund(String id, Transaction incoming) {
         transactionRepository.findTransactionById(id).ifPresent(transaction -> {
            if(incoming.getQuantity() > transaction.getQuantityAvailable()){
                incoming.setQuantity(transaction.getQuantityAvailable());
            }
            float amount;
            int newQuantity = 0;
            if(transaction.getQuantityAvailable() <= 0){
                 return;
            }
            if(incoming.getQuantity() <= transaction.getQuantity()){
                amount = incoming.getQuantity() * transaction.getPricePerUnit();
                newQuantity = transaction.getQuantityAvailable() - incoming.getQuantity();
            } else {
                amount = transaction.getQuantity() * transaction.getPricePerUnit();
            }
            if(newQuantity > 0){
                transaction.setQuantityAvailable(newQuantity);
                transactionRepository.save(transaction);
            } else if(newQuantity == 0){
                transaction.setQuantityAvailable(0);
                transactionRepository.save(transaction);
            }
            Transaction newTransaction = new Transaction(transaction.getCustomerId(),"sell", "Selling of "+transaction.getItemDescription(), incoming.getQuantity(), transaction.getPricePerUnit(), amount, true, null, transaction.getFund_id());
            transactionRepository.insert(newTransaction);

            walletService.depositOneWallet(newTransaction);
         });
    }
}
