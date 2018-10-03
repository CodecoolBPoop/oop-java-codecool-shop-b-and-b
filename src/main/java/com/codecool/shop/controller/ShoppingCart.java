package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.AllCarts;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = {"/shopping-cart"})
public class ShoppingCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new Cart();
        new Cart();
        Cart cart = AllCarts.getCart(1);
        Cart cart2= AllCarts.getCart(2);
        cart.addItem(ProductDaoMem.getInstance().find(1));
        cart.addItem(ProductDaoMem.getInstance().find(2));
        System.out.println(cart);
        System.out.println(cart2);
    }
}
