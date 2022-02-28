package com.microservice.transaction;

import com.microservice.transaction.Customer.Customer;
import com.microservice.transaction.Transaction.Transaction;
import com.microservice.transaction.Wallet.Wallet;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class userData {
    private Optional<Customer> customer;
    private Optional<List<Transaction>> transactions;
    private Optional<Wallet> wallet;

    public userData(Optional<Customer> customer, Optional<List<Transaction>> transaction, Optional<Wallet> wallet) {
        this.customer = customer;
        this.transactions = transaction;
        this.wallet = wallet;
    }
}
