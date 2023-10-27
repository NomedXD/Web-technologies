package by.bsuir.controllers;

import by.bsuir.services.CategoryService;
import by.bsuir.services.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Home servlet. Responsible for home endpoints
 */
@WebServlet("/shop")
public class HomeController extends HttpServlet {
    private final CategoryService categoryService = new CategoryServiceImpl();

    /**
     * Dispatch a home page
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryService.getHomePage(req, resp);
    }
}
