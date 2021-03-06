package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Map params = new HashMap<>();
        String userId = "";


        // SESSION ATTRIBUTE SETTER/GETTER
        try {
            userId = req.getSession(false).getAttribute("id").toString();
        } catch (NullPointerException e) {
            req.getSession().setAttribute("id", userId);
        }
//        System.out.println(req.getSession(false).getAttribute("id"));

        int totalItems = 0;
        int category=1;
        int supplier;

        if (req.getParameter("category") != null) {
            category =Integer.parseInt(req.getParameter("category"));
            params.put("category", productCategoryDataStore.find(category));
            context.setVariable("category", params.get("category"));
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(category)));
        }else if (req.getParameter("supplier") != null){
            supplier = Integer.parseInt(req.getParameter("supplier"));
            params.put("category", supplierDataStore.find(supplier));
            params.put("products", productDataStore.getBy(supplierDataStore.find(supplier)));
        }else {
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(category)));
            params.put("category", productCategoryDataStore.find(category));
            context.setVariable("category", params.get("category"));
        }

        if (CurrentOrders.getOrder(1) != null) {
            totalItems = CurrentOrders.getOrder(1).getTotalItems();
        }



        context.setVariables(params);
        context.setVariable("totalItems", totalItems);
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("recipient", "World");
        context.setVariable("products", params.get("products"));
        engine.process("product/index.html", context, resp.getWriter());
    }



}
