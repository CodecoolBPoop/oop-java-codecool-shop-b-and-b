package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CurrentOrders;
import com.codecool.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/ajax/change-quantity"})
public class ChangeQuantityController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ChangeQuantityController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productname = req.getParameter("productName");
        int newQuantity = Integer.parseInt(req.getParameter("quantity"));
        int userId = (int) req.getSession().getAttribute("id");
        Order currentOrder = CurrentOrders.getOrder(userId);
        currentOrder.modifyQuantity(productname,newQuantity);
        logger.info("New quantity of {} set to {}.",productname,newQuantity);


        resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        resp.getWriter().write("Successfully changed quantity!");
    }
}
