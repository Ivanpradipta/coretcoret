package com.example.springboot.entity;

import org.springframework.hateoas.RepresentationModel;

public class Customer extends RepresentationModel<Customer> {
    private long id;

    private String name;

    private double credit;

    public Customer() {
    }

    public Customer(final String name, final double credit) {
        this.name = name;
        this.credit = credit;
    }

    public void addCredit(final double quantity) {
        this.credit += quantity;
    }

    public void deductCredit(final double quantity) {
        this.credit -= quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(final double credit) {
        this.credit = credit;
    }
}
