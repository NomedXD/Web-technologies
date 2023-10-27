package by.bsuir.controllers;

import by.bsuir.enums.PagesPathEnum;
import by.bsuir.services.UserService;
import by.bsuir.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Authentication servlet. Responsible for login endpoints
 */
@WebServlet("/login")
public class AuthenticationController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * Dispatch an login page
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(PagesPathEnum.LOG_IN_PAGE.getPath()).forward(req, resp);
    }

    /**
     * Invoke service method to sign in
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req, resp);
    }
}
