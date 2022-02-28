package com.microservice.transaction.Wallet;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Data
@Document
public class Wallet {
    @Id
    private String id;
    private String customerId;
    private String currencyType;
    private Float currencyAmount;

    public Wallet(String customerId, String currencyType, Float currencyAmount) {
        this.customerId = customerId;
        this.currencyType = currencyType;
        this.currencyAmount = currencyAmount;
    }
}
