package by.bsuir.controllers;

import by.bsuir.enums.PagesPathEnum;
import by.bsuir.enums.RequestParamsEnum;
import by.bsuir.services.UserService;
import by.bsuir.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Account servlet. Responsible for account page endpoints
 */
@WebServlet("/account")
public class AccountController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * Dispatch an account page. If user does not log in dispatch a login page
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(RequestParamsEnum.USER.getValue()) == null) {
            req.setAttribute(RequestParamsEnum.DEF_SUC_URL.getValue(), "/account");
            req.getRequestDispatcher(PagesPathEnum.LOG_IN_PAGE.getPath()).forward(req, resp);
        } else {
            userService.getAccountPage(req, resp);
        }
    }

    /**
     * Invoke service method to update account
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.updateAccountData(req, resp);
    }
}
