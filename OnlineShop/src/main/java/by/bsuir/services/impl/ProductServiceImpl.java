package by.bsuir.services.impl;

import by.bsuir.domain.Product;
import by.bsuir.enums.PagesPathEnum;
import by.bsuir.exception.NoSuchCategoryException;
import by.bsuir.exception.NoSuchEntityException;
import by.bsuir.exception.NoSuchProductException;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.ProductRepository;
import by.bsuir.repositories.impl.ProductRepositoryImpl;
import by.bsuir.services.CategoryService;
import by.bsuir.services.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository = new ProductRepositoryImpl();
    CategoryService categoryService = new CategoryServiceImpl();
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

    @Override
    public void getProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchProductException(productId));
            Integer categoryId = product.getCategoryId();
            request.setAttribute("product", product);
            request.setAttribute("categoryName", categoryService.getCategoryById(categoryId).orElseThrow(() -> new NoSuchCategoryException(categoryId)).getName());
            request.getRequestDispatcher(PagesPathEnum.PRODUCT_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath());
            requestDispatcher.forward(request, response);
        } catch (NoSuchEntityException e)  {
            LoggerFactory.getLogger(ProductRepositoryImpl.class).error(e.getLoggerMessage());
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath());
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public Optional<Product> getProductById(Integer productId) throws SQLExecutionException {
        return productRepository.findById(productId);
    }
}
