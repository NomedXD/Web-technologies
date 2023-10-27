package by.bsuir.controllers;

import by.bsuir.services.UserService;
import by.bsuir.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Logout servlet. Responsible for logout endpoints
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * Invoke service method to logout user from account
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.logout(req, resp);
    }
}
