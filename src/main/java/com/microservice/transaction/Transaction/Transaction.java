package com.microservice.transaction.Transaction;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Transaction {
    @Id
    private String id;
    private String customerId;
    private String type;
    private String itemDescription;
    private Integer quantity;
    private Float pricePerUnit;
    private Float amount;
    private Boolean sold;
    private Integer quantityAvailable;
    private Integer fund_id;

    public Transaction(String customerId, String type, String itemDescription, Integer quantity, Float pricePerUnit, Float amount, Boolean sold, Integer quantityAvailable, Integer fund_id) {
        this.customerId = customerId;
        this.type = type;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.amount = amount;
        this.sold = sold;
        this.quantityAvailable = quantityAvailable;
        this.fund_id = fund_id;
    }
}
