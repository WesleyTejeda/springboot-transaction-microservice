package com.microservice.transaction.Transaction;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Optional<List<Transaction>>findAllTransactionByCustomerId(String customerId);

    Optional<Transaction> findTransactionById(String id);
}
