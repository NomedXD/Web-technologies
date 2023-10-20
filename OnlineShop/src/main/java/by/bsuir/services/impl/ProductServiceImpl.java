package by.bsuir.services.impl;

import by.bsuir.domain.Product;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.ProductRepository;
import by.bsuir.repositories.impl.ProductRepositoryImpl;
import by.bsuir.services.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository = new ProductRepositoryImpl();
    @Override
    public Product create(Product entity) {
        return null;
    }

    @Override
    public List<Product> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void getCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("products", productRepository.getProductsByCategoryId(Integer.parseInt(request.getParameter("categoryId"))));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.CATEGORY_PAGE.getPath());
            requestDispatcher.forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath());
            requestDispatcher.forward(request, response);
        }
    }
}
