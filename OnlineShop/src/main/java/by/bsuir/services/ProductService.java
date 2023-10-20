package by.bsuir.services;

import by.bsuir.domain.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ProductService extends BaseService<Product>{
    void getCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
