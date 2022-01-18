package com.example.springhomework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HomeworkApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HomeworkConfig.class);

        for (int i = 0; i < 5; i++) {
            Buyer buyer = context.getBean(Buyer.class);
            buyer.purchase();
            buyer.showCart();
        }
    }
}
