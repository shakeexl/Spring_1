package ru.geekbrains.springlesson4.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Product {

    private Long id;
    private String title;
    private BigDecimal cost;

}
