package net.fullstack10.chap01.login;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet(name = "/Login", value = "/login.do")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String user_id = req.getParameter("user_id");
        String pwd = req.getParameter("pwd");

        System.out.println("====================");
        System.out.println("user_id: " + user_id);
        System.out.println("pwd: " + pwd);
        System.out.println("====================");

        res.sendRedirect("/");
    }
}