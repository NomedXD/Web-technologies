package by.bsuir.controllers;

import by.bsuir.services.UserService;
import by.bsuir.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/account")
public class AccountController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            req.setAttribute("defaultSuccessUrl", "/account");
            req.getRequestDispatcher(req.getContextPath() + "/login").forward(req, resp);
        } else {
            userService.getAccountPage(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.updateAccountData(req, resp);
    }
}
