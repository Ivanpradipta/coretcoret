package com.example.springboot.repository;

import com.example.springboot.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Product save(Product newProduct) {
        products.add(newProduct);
        return newProduct;
    }

    public Optional<Product> findById(Long id) {
        Product result = products.stream()
                .filter(product -> id == product.getId())
                .findAny()
                .orElse(null);
        return Optional.ofNullable(result);
    }

    public void deleteById(Long id) {
    }

    


}
