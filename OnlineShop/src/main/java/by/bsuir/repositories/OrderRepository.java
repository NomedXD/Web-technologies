package by.bsuir.repositories;

import by.bsuir.domain.Category;
import by.bsuir.domain.Order;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;

public interface OrderRepository extends BaseRepository {
    Order create(Order entity);

    List<Order> read() throws SQLExecutionException;

    Order update(Order entity);

    void delete(int id);

    List<Order> findAllByUserId(Integer userId) throws SQLExecutionException;
}
