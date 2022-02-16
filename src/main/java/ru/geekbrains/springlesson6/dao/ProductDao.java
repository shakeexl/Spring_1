package ru.geekbrains.springlesson6.dao;

import ru.geekbrains.springlesson6.entity.Product;

public interface ProductDao {
    Iterable<Product> findAll();
    Product findById(Long id);
    void saveOrUpdate(Product product);
    void deleteById(Long id);
}
