package by.bsuir.services;

import by.bsuir.domain.Order;
import by.bsuir.exception.SQLExecutionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface OrderService extends BaseService<Order>{
    List<Order> getOrdersByUserId(Integer userId) throws SQLExecutionException;
    void applyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
