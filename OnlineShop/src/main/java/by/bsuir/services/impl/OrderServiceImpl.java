package by.bsuir.services.impl;

import by.bsuir.domain.Order;
import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.OrderRepository;
import by.bsuir.repositories.impl.OrderRepositoryImpl;
import by.bsuir.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository = new OrderRepositoryImpl();
    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public List<Order> read() throws SQLExecutionException {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) throws SQLExecutionException {
        return orderRepository.findAllByUserId(userId);
    }
}
