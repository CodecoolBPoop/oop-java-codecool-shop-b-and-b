package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CurrentOrders;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        new Order();
        CurrentOrders.getOrder(1).addItem(ProductDaoMem.getInstance().find(1));
        CurrentOrders.getOrder(1).addItem(ProductDaoMem.getInstance().find(1));
        CurrentOrders.getOrder(1).addItem(ProductDaoMem.getInstance().find(1));
        CurrentOrders.getOrder(1).addItem(ProductDaoMem.getInstance().find(1));
        CurrentOrders.getOrder(1).addItem(ProductDaoMem.getInstance().find(2));
        CurrentOrders.getOrder(1).addItem(ProductDaoMem.getInstance().find(2));
        CurrentOrders.getOrder(1).addItem(ProductDaoMem.getInstance().find(3));
        System.out.println(CurrentOrders.getOrder(1));
        engine.process("product/shopping-cart.html", context, resp.getWriter());
    }
}
