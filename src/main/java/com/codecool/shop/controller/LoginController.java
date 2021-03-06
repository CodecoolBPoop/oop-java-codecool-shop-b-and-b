package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJDBC;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/ajax/login"})
public class LoginController extends  HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDao userStore = UserDaoJDBC.getInstance();
        User user = userStore.getUser(email, password);
        if (user == null){
            resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Username or password is incorrect!");
        } else {
            req.getSession().setAttribute("id", user.getId());
            resp.sendRedirect("/");
        }
    }
}
