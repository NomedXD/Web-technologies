package by.bsuir.controllers;

import by.bsuir.services.OrderService;
import by.bsuir.services.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Account servlet. Responsible for order deletion
 */
@WebServlet("/account/remove_order")
public class RemoveOrderController extends HttpServlet {
    private final OrderService orderService = new OrderServiceImpl();

    /**
     * Invoke service method to remove user order
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if occurs dispatch error
     * @throws IOException      if occurs dispatch error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.removeOrder(req, resp);
    }
}
