package com.example.springhomework;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShopController implements ShopControllerInterface {

    private final ProductRepository productRepository;
    private Cart cart;

    @Lookup
    @Override
    public Cart getCart(){
        return null;
    }

    public void showAssortment() {
        System.out.println(productRepository.findAll());
    }

    public void addProductToCartById(Integer id) {
        productRepository.findById(id).ifPresent(cart);
    }

    public void deleteProductFromCartById(Integer id) {
        cart.deleteByProductId(id);
    }

    public void showProductsInCart(){
        cart.showList();
    }

    public void enterToShop(){
        this.cart = getCart();
    }
}
