package by.bsuir.controllers;

import by.bsuir.services.DiscountCodeService;
import by.bsuir.services.impl.DiscountCodeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Category servlet. Responsible for discount code endpoints
 */
@WebServlet("/cart/check_code")
public class DiscountCodeController extends HttpServlet {
    private final DiscountCodeService discountCodeService = new DiscountCodeServiceImpl();

    /**
     * Invoke service method to apply discount code
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        discountCodeService.applyDiscountCodeToCart(req, resp);
    }
}
