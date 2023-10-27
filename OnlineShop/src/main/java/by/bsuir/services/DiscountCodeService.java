package by.bsuir.services;

import by.bsuir.domain.DiscountCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface DiscountCodeService extends BaseService<DiscountCode> {
    /**
     * Applies a promo code to the user's shopping cart.
     * <p>
     * Pre-checks whether such a promo code exists. If the promo code exists, the promo code is added to the shopping cart. Otherwise, an error message is placed in the request parameters, which is displayed on the page
     * <p>
     * Redirects to the shopping cart page
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void applyDiscountCodeToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
