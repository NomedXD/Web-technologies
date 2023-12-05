package by.bsuir.project.repositories;

import by.bsuir.project.domain.Order;
import by.bsuir.project.domain.User;
import by.bsuir.project.exception.EntityOperationException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findUserByMail(String mail);

    Optional<User> findById(Integer id);

    List<Order> findOrdersByUserId(Integer id) throws EntityOperationException;
}
