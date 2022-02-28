package com.microservice.transaction.Customer;

import com.microservice.transaction.Transaction.TransactionRepository;
import com.microservice.transaction.Transaction.TransactionService;
import com.microservice.transaction.Wallet.WalletRepository;
import com.microservice.transaction.Wallet.WalletService;
import com.microservice.transaction.userData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;
    private final WalletService walletService;

    public CustomerService(CustomerRepository customerRepository, TransactionRepository transactionRepository, TransactionService transactionService, WalletRepository walletRepository, WalletService walletService) {
        this.customerRepository = customerRepository;
        this.transactionService = transactionService;
        this.walletService = walletService;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer() {
        Customer customer = new Customer();
        customerRepository.insert(customer);
        return customer;
    }

    public Optional<userData> getOneCustomer(String id) {
        Optional<userData> data = Optional.of((new userData(customerRepository.findCustomerById(id), transactionService.getUserTransactions(id), walletService.getUserWallet(id))));
        System.out.println(data);
        return data;
    };
}
