package by.bsuir.repositories;

import by.bsuir.domain.Role;
import by.bsuir.domain.User;
import by.bsuir.exception.SQLExecutionException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository {
    /**
     * @param entity entity to be persisted
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void create(User entity) throws SQLExecutionException;

    /**
     * @return list of all entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<User> read() throws SQLExecutionException;

    /**
     * @param entity entity to be updated
     * @return updated entity in database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    User update(User entity) throws SQLExecutionException;

    /**
     * @param id used to remove entity from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    void delete(int id) throws SQLExecutionException;

    /**
     * @param userMail used to find entity in database
     * @return optional of entity. Optional is null if entity was not found
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    Optional<User> findUserByMail(String userMail) throws SQLExecutionException;

    /**
     * @param userId used to find entity in database
     * @return list of founded entities from database
     * @throws SQLExecutionException thrown out if occurs exception via database operation
     */
    List<Role> findRolesByUserId(Integer userId) throws SQLExecutionException;
}
