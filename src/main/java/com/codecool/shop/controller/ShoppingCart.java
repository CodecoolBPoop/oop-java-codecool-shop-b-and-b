package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CurrentOrders;
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
        int userId = 0;
        if ((req.getSession().getAttribute("id")) != null){
            try {
                userId = (int) (req.getSession().getAttribute("id"));
            } catch (ClassCastException e) {
                // pass
            }
            if (CurrentOrders.getOrder(userId) == null) {
                new Order(userId);
            }
        }
        context.setVariable("sumOfPrice",CurrentOrders.getOrder(userId).getTotalPrice());
        context.setVariable("orderid",1);
        context.setVariable("items", CurrentOrders.getOrder(userId).getItems());
        engine.process("product/shopping-cart.html", context, resp.getWriter());
    }
}
