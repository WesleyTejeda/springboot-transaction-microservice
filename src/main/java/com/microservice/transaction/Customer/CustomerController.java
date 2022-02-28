package com.microservice.transaction.Customer;

import com.microservice.transaction.Wallet.Wallet;
import com.microservice.transaction.Wallet.WalletService;
import com.microservice.transaction.userData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final WalletService walletService;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService, WalletService walletService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.walletService = walletService;
    }
    @GetMapping(path = "")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "{id}")
    public Optional<userData> getOneCustomer(@PathVariable("id") String id){
        return customerService.getOneCustomer(id);
    }

    @PostMapping(path = "create")
    public String createCustomer(){
        Customer customer = customerService.createCustomer();
        walletService.createWallet(customer.getId());
        return "Customer created and instantiated wallet";
    }


}
