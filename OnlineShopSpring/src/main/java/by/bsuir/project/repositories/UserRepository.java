package by.bsuir.project.repositories;

import by.bsuir.project.domain.Order;
import by.bsuir.project.domain.User;
import by.bsuir.project.exception.EntityOperationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findUserByMail(String mail);

    Optional<User> findById(Integer id);

    @Query("select o from Order o where o.user.id = :id")
    List<Order> findOrdersByUserId(@Param("id") Integer id) throws EntityOperationException;
}
