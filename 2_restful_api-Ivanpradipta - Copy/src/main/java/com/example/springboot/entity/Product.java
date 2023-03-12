package com.example.springboot.entity;

import org.springframework.hateoas.RepresentationModel;

public class Product extends RepresentationModel<Product> {

    private long id;

    private String name;

    private int stock;

    public Product() {
    }

    public Product(final long id, final String name, final int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public void addStock(final int quantity) {
        this.stock += quantity;
    }

    public void deductStock(final int quantity) {
        this.stock -= quantity;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(final int stock) {
        this.stock = stock;
    }
}