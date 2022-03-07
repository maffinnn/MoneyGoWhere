package com.cz2006.group3.servlet;

import com.cz2006.group3.bean.DBConnector;
import com.cz2006.group3.bean.UserData;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet{

    // POST请求时处理用户登录:
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserData matchedUser = null;
        try {
            matchedUser = DBConnector.queryUser(email.toLowerCase());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String expectedPassword = matchedUser.getPassword();
        if (expectedPassword != null && expectedPassword.equals(password)) {
            req.getSession().setAttribute("email", matchedUser.getEmail());
            resp.sendRedirect("/home");
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
