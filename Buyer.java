package com.example.springhomework;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@RequiredArgsConstructor
@Component
@Scope("prototype")
public class Buyer {
    private final ShopController shopController;
    public Integer productsNumber;

    Random r = new Random();

    @PostConstruct
    public void init(){
        productsNumber = r.nextInt(9) + 2;
        shopController.enterToShop();
    }

    public void purchase() {
        System.out.println("Buyer wants to buy " + productsNumber + " products");
        int i = 0;
        while (i <= productsNumber) {
            shopController.addProductToCartById(r.nextInt(5));
            i++;
        }
        shopController.deleteProductFromCartById(r.nextInt(5));
    }

    public void showCart() {
        System.out.println("Buyer bought ");
        shopController.showProductsInCart();
        System.out.println("-------------------------------------------------");
    }
}
