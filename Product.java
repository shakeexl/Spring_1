package com.example.springhomework;

//1. Есть класс Product (id, название, цена). Товары хранятся в бине ProductRepository, в виде List<Product>, при старте в него нужно добавить 5 любых товаров.
//        2. ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно добавлять товары, а также удалять товары по id.
//        3. Написать консольное приложение, позволяющее управлять корзиной.
//        4. При каждом запросе корзины из контекста, должна создаваться новая корзина.

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private Integer id;
    private String name;
    //все суммы должны хранится в BigDecimal, а не в Double
    private Double cost;
}
