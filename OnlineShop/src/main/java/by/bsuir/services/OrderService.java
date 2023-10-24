package by.bsuir.services;

import by.bsuir.domain.Order;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface OrderService extends BaseService<Order>{
    List<Order> getOrdersByUserId(Integer userId) throws SQLExecutionException;
}
