package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Cart;


import java.util.LinkedList;
import java.util.List;

public class AllCarts {
    private static List<Cart> carts = new LinkedList<>();

    public static void addCart(Cart cart){
        carts.add(cart);
    }

    public static void removeCart(Cart cart){
        carts.remove(cart);
    }

    public static Cart getCart(int id){
        for (Cart cart: carts) {
            if (cart.getId() == id){
                return cart;
            }
        }
        return null;
    }

    public static int createNewCartId(){
        if (carts.size()==0){
            return 1;
        }
        return carts.get(carts.size()-1).getId()+1;
    }
}
