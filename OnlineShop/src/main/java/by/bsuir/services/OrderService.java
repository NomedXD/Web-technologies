package by.bsuir.services;

import by.bsuir.domain.Order;
import by.bsuir.exception.SQLExecutionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface OrderService extends BaseService<Order> {
    /**
     * @param userId used to find entity in database
     * @return list of founded entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<Order> getOrdersByUserId(Integer userId) throws SQLExecutionException;

    /**
     * Creates an order.
     * <p>
     * Creates an order entity, moves the contents of the basket to the order. Creates an order in the database and clears the cart.
     * <p>
     * Redirects to the shopping cart page
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void applyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Completely removes the user's order from the database
     *
     * @param request  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    void removeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
