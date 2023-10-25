package by.bsuir.services;

import by.bsuir.domain.DiscountCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface DiscountCodeService extends BaseService<DiscountCode> {
    void applyDiscountCodeToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
