package ru.geekbrains.springlesson4.service;

import ru.geekbrains.springlesson4.repository.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProductList();

    Product getProductById(Long id);

    void saveOrUpdate(Product product);

    void deleteById(Long id);
}