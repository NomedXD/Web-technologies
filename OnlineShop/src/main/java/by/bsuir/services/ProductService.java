package by.bsuir.services;

import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface ProductService extends BaseService<Product> {
    void getCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void getProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    Optional<Product> getProductById(Integer productId) throws SQLExecutionException;
}
