package by.bsuir.services.impl;

import by.bsuir.domain.Cart;
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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository = new ProductRepositoryImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public void create(Product entity) throws SQLExecutionException {
        productRepository.create(entity);
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
            request.getRequestDispatcher(PagesPathEnum.CATEGORY_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
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
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        } catch (NoSuchEntityException e) {
            LoggerFactory.getLogger(ProductRepositoryImpl.class).error(e.getLoggerMessage());
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    @Override
    public void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        try {
            Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchProductException(productId));
            Cart cart;
            if (session.getAttribute("cart") == null) {
                cart = new Cart();
            } else {
                cart = (Cart) session.getAttribute("cart");
            }
            cart.addProduct(product);
            session.setAttribute("cart", cart);
            Integer categoryId = product.getCategoryId();
            request.setAttribute("product", product);
            request.setAttribute("categoryName", categoryService.getCategoryById(categoryId).orElseThrow(() -> new NoSuchCategoryException(categoryId)).getName());
            request.getRequestDispatcher(PagesPathEnum.PRODUCT_PAGE.getPath()).forward(request, response);
        } catch (SQLExecutionException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        } catch (NoSuchEntityException e) {
            LoggerFactory.getLogger(ProductRepositoryImpl.class).error(e.getLoggerMessage());
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    @Override
    public void removeProductFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ((Cart) session.getAttribute("cart")).removeProduct(Integer.parseInt(request.getParameter("productId")));
        request.getRequestDispatcher("/cart").forward(request, response);
    }

    @Override
    public Optional<Product> getProductById(Integer productId) throws SQLExecutionException {
        return productRepository.findById(productId);
    }
}
