package by.bsuir.project.services;

import by.bsuir.project.domain.Cart;
import by.bsuir.project.domain.Product;
import by.bsuir.project.domain.Search;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

public interface ProductService extends BaseService<Product> {
    List<Product> getCategoryProducts(int categoryId);

    Optional<Product> getProductById(int id);

    ModelAndView getSearchedPaginatedProducts(Search search, Integer currentPage, Integer pageSize);

    ModelAndView getPaginatedProductsByCategoryId(Integer categoryId, Integer currentPage, Integer pageSize);

    Long getCountOfAllProducts();

    Long getCountAppropriateProducts(Search search);

    ModelAndView applyProductsQuantity(Cart cart, HttpServletRequest request);
}
