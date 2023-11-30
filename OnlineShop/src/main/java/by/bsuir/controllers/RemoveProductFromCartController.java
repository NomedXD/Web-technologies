package by.bsuir.controllers;

import by.bsuir.services.ProductService;
import by.bsuir.services.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Cart servlet. Responsible for product deletion
 */
@WebServlet("/cart/remove")
public class RemoveProductFromCartController extends HttpServlet {
    private final ProductService productService = new ProductServiceImpl();

    /**
     * Invoke service method to remove product from cart
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService.removeProductFromCart(req, resp);
    }
}
