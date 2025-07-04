package com.example.Ecom_BackEnd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service

public class CartService {
    private List<CartItem> cart = new ArrayList<>();

    public void addToCart(Product product) {
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        cart.add(new CartItem(product, 1));
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public double getTotal() {
        return cart.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }

    public void clearCart() {
        cart.clear();
    }
}
