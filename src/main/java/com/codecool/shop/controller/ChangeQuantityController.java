package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CurrentOrders;
import com.codecool.shop.model.Order;

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
        final Integer usedId = 1;
        String productname = req.getParameter("productName");
        int newQuantity = Integer.parseInt(req.getParameter("quantity"));
        System.out.println(newQuantity);
        System.out.println(productname);
        Order currentOrder = CurrentOrders.getOrder(usedId);
        currentOrder.modifyQuantity(productname,newQuantity);

        resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        resp.getWriter().write("Successfully changed quantity!");
    }
}
