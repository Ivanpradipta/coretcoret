package com.example.springboot.entity;

public class Order {

    private long id;
    private Customer customer;
    private double totalCost;
    private Product product;
    private int productQuantity;
    public Order() {
    }

    public Order(final Customer customer, final double totalCost, final Product product, final int productQuantity) {
        this.customer = customer;
        this.totalCost = totalCost;
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(final double totalCost) {
        this.totalCost = totalCost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(final int productQuantity) {
        this.productQuantity = productQuantity;
    }
}