package com.microservice.transaction.Customer;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Customer {
    @Id
    private String id;

    public Customer() {
    }
}
