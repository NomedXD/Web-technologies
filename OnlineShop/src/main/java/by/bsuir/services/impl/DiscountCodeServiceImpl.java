package by.bsuir.services.impl;

import by.bsuir.domain.Cart;
import by.bsuir.domain.DiscountCode;
import by.bsuir.enums.EshopConstants;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.DiscountCodeRepository;
import by.bsuir.repositories.impl.DiscountCodeRepositoryImpl;
import by.bsuir.services.DiscountCodeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DiscountCodeServiceImpl implements DiscountCodeService {
    private final DiscountCodeRepository discountCodeRepository = new DiscountCodeRepositoryImpl();
    @Override
    public DiscountCode create(DiscountCode entity) {
        return null;
    }

    @Override
    public List<DiscountCode> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public DiscountCode update(DiscountCode entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void applyDiscountCodeToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Optional<DiscountCode> discountCode = discountCodeRepository.findDiscountCodeByName(request.getParameter("discountCode"));
            if (discountCode.isPresent()) {
                request.getSession().setAttribute("cart", Cart.applyDiscountCode(discountCode.get(), ((Cart) request.getSession().getAttribute("cart"))));
            } else {
                request.setAttribute("discountCodeMessage", EshopConstants.errorDiscountCodeMessage);
            }
            request.getRequestDispatcher(PagesPathEnum.CART_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }
}
