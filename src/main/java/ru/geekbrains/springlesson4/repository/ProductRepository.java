package ru.geekbrains.springlesson4.repository;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        for (long i = 1; i < 11; i++) {
            BigDecimal rndPrice = BigDecimal.valueOf(new Random().nextInt(100000)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
            productMap.put(i, new Product(i, "Product " + i, rndPrice));
        }
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            Long id = Collections.max(productMap.keySet()) + 1;
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    public Product findById(Long id) {
        return productMap.get(id);
    }

    public void deleteById(Long id) {
        productMap.remove(id);
    }
}
