package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        int category=1;

        if (req.getParameter("category") != null) {
            category =Integer.parseInt(req.getParameter("category"));
        }
        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(category));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(category)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariables(params);
        context.setVariable("categories", ProductCategoryDaoMem.getInstance().getAll());
        context.setVariable("recipient", "World");
        context.setVariable("category", params.get("category"));
        context.setVariable("products", params.get("products"));
        engine.process("product/index.html", context, resp.getWriter());
    }

}
