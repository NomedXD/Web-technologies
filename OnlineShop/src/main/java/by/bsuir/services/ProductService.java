package by.bsuir.services;

import by.bsuir.domain.Product;
import by.bsuir.exception.SQLExecutionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface ProductService extends BaseService<Product> {
    /**
     * Returns the category page
     * <p>
     * A list of all products of the category is placed in the request parameters
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void getCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Returns the product page
     * <p>
     * The Product object is placed in the request parameters
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void getProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Adds an item to the cart
     * <p>
     * Searches for the product in the database by id. If the product is found, it is added to the cart
     * <p>
     * Returns the product page
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Removes an item from the shopping cart
     * <p>
     * Returns the cart page
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void removeProductFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    Optional<Product> getProductById(Integer productId) throws SQLExecutionException;
}
