package ru.geekbrains.service;

import ru.geekbrains.repository.Product;
import java.util.List;

public interface ProductService {

    List<Product> getProductList();

    Product getProductById(Long id);

    void saveOrUpdate(Product product);

    void deleteById(Long id);
}