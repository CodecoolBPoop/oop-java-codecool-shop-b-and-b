package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.CurrentOrders;
import com.codecool.shop.dao.implementation.UserDaoJDBC;
import com.codecool.shop.model.Order;
import com.codecool.shop.hash.PasswordAuthentication;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = {"/sign-up"})
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/sign-up.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UserDao userStore = UserDaoJDBC.getInstance();
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String phoneNumber = req.getParameter("phone_number");
        String shippingAddress = req.getParameter("shipping_address");
        String billingAddress = req.getParameter("billing_address");
        String hashedPassword = new PasswordAuthentication().hash(password);
        System.out.println("email: " + email +
                            "\npassword: " + password +
                            "\nfirst name: " + firstName +
                            "\nlast name: " + lastName +
                            "\nphone number: " + phoneNumber +
                            "\nshipping address: " + shippingAddress +
                            "\nbilling address: " + billingAddress);
        System.out.println(hashedPassword);
        userStore.add(new User(email, hashedPassword, firstName, lastName, phoneNumber, shippingAddress, billingAddress));
        resp.sendRedirect("/");
    }
}
