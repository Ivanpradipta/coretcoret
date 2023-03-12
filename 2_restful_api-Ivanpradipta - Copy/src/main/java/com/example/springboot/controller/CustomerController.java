package com.example.springboot.controller;

import com.example.springboot.entity.Customer;
import com.example.springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    CollectionModel<Customer> getAll() {
        List<Customer> allCustomers = repository.findAll();
        for (Customer customer : allCustomers) {
            long customerId = customer.getId();
            customer.removeLinks();
            Link selfLink = linkTo(CustomerController.class).slash("customers").slash(customerId).withSelfRel();
            customer.add(selfLink);
        }

        Link link = linkTo(CustomerController.class).slash("customers").withSelfRel();
        return CollectionModel.of(allCustomers, link);
    }

    @GetMapping("/customers/{id}")
    EntityModel<Customer> getOne(@PathVariable Long id) {
        final Customer customer = repository.findById(id).get();
        return EntityModel.of(customer, //
                linkTo(CustomerController.class).slash("customers").slash(id).withSelfRel(),
                linkTo(CustomerController.class).slash("customers").withRel("all"));
    }

    @PostMapping("/customers")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newCustomer));
    }

}
