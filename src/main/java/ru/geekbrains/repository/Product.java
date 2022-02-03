package ru.geekbrains.repository;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private Long id;
    private String title;
    private BigDecimal cost;

    public Product(Long id, String title, BigDecimal cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
