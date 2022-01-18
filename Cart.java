package com.example.springhomework;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component("cart")
@Scope("prototype")
public class Cart implements Consumer<Product> {
    private final Map<Integer, Product> products = new HashMap<>();

    public void add(Product product) {
        products.put(product.getId(), product);
    }

    public void deleteByProductId(Integer id) {
        products.remove(id);
    }

    public void showList() {
        System.out.println(products);
    }

    @Override
    public void accept(Product product) {
        add(product);
    }
}
