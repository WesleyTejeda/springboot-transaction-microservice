package com.microservice.transaction.Wallet;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WalletRepository extends MongoRepository<Wallet, String> {
    Optional<Wallet> findWalletByCustomerId(String customerId);
}
