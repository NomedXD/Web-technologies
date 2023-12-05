package by.bsuir.project.repositories;

import by.bsuir.project.domain.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {
    Long countAllByUserId(Integer userId);

    List<Order> findAllByUserId(Integer userId, Integer currentPage, Integer pageSize);
}
