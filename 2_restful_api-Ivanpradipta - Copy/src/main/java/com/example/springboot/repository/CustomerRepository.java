package com.example.springboot.repository;

import com.example.springboot.entity.Customer;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    List<Customer> customers = new ArrayList<>();

    public List<Customer> findAll() {
        return customers;
    }

    public Customer save(Customer newCustomer) {
        customers.add(newCustomer);
        return newCustomer;
    }

    public Optional<Customer> findById(Long id) {
        Customer result = customers.stream()
                .filter(customer -> id == customer.getId())
                .findAny()
                .orElse(null);
        return Optional.ofNullable(result);
    }
}
