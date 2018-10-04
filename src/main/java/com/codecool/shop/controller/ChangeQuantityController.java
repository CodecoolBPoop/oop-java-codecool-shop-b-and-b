package com.codecool.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/ajax/change-quantity"})
public class ChangeQuantityController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productname = req.getParameter("productName");
        int newQuantity = Integer.parseInt(req.getParameter("quantity"));
        System.out.println(newQuantity);
        System.out.println(productname);
    }
}
