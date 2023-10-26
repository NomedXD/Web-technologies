package by.bsuir.controllers;

import by.bsuir.services.OrderService;
import by.bsuir.services.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/account/remove_order")
public class RemoveOrderController extends HttpServlet {
    private final OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.removeOrder(req, resp);
    }
}
