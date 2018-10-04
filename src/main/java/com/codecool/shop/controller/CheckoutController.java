package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CurrentOrders;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order currentOrder = CurrentOrders.getOrder(1);
        currentOrder.setFirstName(req.getParameter("first_name"));
        currentOrder.setLastName(req.getParameter("last_name"));
        currentOrder.setEmail(req.getParameter("email"));
        currentOrder.setPhone(req.getParameter("phone"));
        currentOrder.setShippingAddress(req.getParameter("shipping_address"));
        currentOrder.setBillingAdrress(req.getParameter("billing_address"));
        resp.sendRedirect("/payment?id=" + currentOrder.getId());
    }
}