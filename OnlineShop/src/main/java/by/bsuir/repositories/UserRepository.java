package by.bsuir.repositories;

import by.bsuir.domain.User;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository{
    void create(User entity) throws SQLExecutionException;
    List<User> read() throws SQLExecutionException;
    User update(User entity) throws SQLExecutionException;
    void delete(int id);
    Optional<User> findUserByMail(String userMail) throws SQLExecutionException;
}
