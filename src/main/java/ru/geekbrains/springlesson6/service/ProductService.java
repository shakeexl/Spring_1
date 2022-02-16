package ru.geekbrains.springlesson6.service;

import ru.geekbrains.springlesson6.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductList();

    Product getProductById(Long id);

    void save(Product product);

    void deleteById(Long id);
}