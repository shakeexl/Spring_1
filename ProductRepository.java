package com.example.springhomework;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("productRepository")
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(0, "Mouse", 4000d));
        products.add(new Product(1, "Keyboard", 8000d));
        products.add(new Product(2, "Monitor", 10000d));
        products.add(new Product(3, "Headphones", 6000d));
        products.add(new Product(4, "Phone", 20000d));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Integer id) {
        if (id < (products.size()) && id >= 0) {
            return Optional.of(products.get(id));
        } else {
            return Optional.empty();
        }
    }
}
