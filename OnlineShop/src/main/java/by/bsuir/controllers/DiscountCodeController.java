package by.bsuir.controllers;

import by.bsuir.services.DiscountCodeService;
import by.bsuir.services.impl.DiscountCodeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cart/check_code")
public class DiscountCodeController extends HttpServlet {
    private final DiscountCodeService discountCodeService = new DiscountCodeServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        discountCodeService.applyDiscountCodeToCart(req, resp);
    }
}
